/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.scaladsl

import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization._
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.adapter._
import org.apache.pekko.kafka._
import org.apache.pekko.kafka.scaladsl.Producer
import org.apache.pekko.stream.connectors.elasticsearch.scaladsl.ElasticsearchSource
import org.apache.pekko.stream.scaladsl.{ Sink, Source }
import org.apache.pekko.Done
import org.apache.pekko.stream.connectors.elasticsearch._
import org.slf4j.LoggerFactory
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.utility.DockerImageName
import samples.scaladsl.JsonFormats._
import spray.json._

import scala.collection.immutable
import scala.concurrent.Future

trait Helper {

  final val log = LoggerFactory.getLogger(getClass)

  // TestContainers: start Elasticsearch in Docker
  val elasticsearchContainer = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.2")
  elasticsearchContainer.start()
  val connectionSettings = ElasticsearchConnectionSettings("http://" + elasticsearchContainer.getHttpHostAddress)

  // TestContainers: start Kafka in Docker
  // [[https://hub.docker.com/r/confluentinc/cp-kafka/tags Available Docker images]]
  // [[https://docs.confluent.io/current/installation/versions-interoperability.html Kafka versions in Confluent Platform]]
  val kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.1")) // contains Kafka 2.4.x
  kafka.start()
  val kafkaBootstrapServers = kafka.getBootstrapServers

  def writeToKafka(topic: String, movies: immutable.Iterable[Movie])(implicit actorSystem: ActorSystem[_]) = {
    val kafkaProducerSettings = ProducerSettings(actorSystem.toClassic, new IntegerSerializer, new StringSerializer)
      .withBootstrapServers(kafkaBootstrapServers)

    val producing: Future[Done] = Source(movies)
      .map { movie =>
        log.debug("producing {}", movie)
        new ProducerRecord(topic, Int.box(movie.id), movie.toJson.compactPrint)
      }
      .runWith(Producer.plainSink(kafkaProducerSettings))
    producing.foreach(_ => log.info("Producing finished"))(actorSystem.executionContext)
    producing
  }

  def readFromElasticsearch(indexName: String)(implicit actorSystem: ActorSystem[_]): Future[immutable.Seq[Movie]] = {
    val reading = ElasticsearchSource
      .typed[Movie](ElasticsearchParams.V7(indexName), """{"match_all": {}}""",
        ElasticsearchSourceSettings(connectionSettings))
      .map(_.source)
      .runWith(Sink.seq)
    reading.foreach(_ => log.info("Reading finished"))(actorSystem.executionContext)
    reading
  }

  def stopContainers() = {
    kafka.stop()
    elasticsearchContainer.stop()
  }
}
