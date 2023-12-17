package samples.javadsl;

import static org.apache.pekko.actor.typed.javadsl.Adapter.*;

import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.kafka.ProducerSettings;
import org.apache.pekko.kafka.javadsl.Producer;
import org.apache.pekko.stream.connectors.elasticsearch.*;
import org.apache.pekko.stream.connectors.elasticsearch.javadsl.ElasticsearchSource;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.concurrent.CompletionStage;

public class Helper {

  private static final Logger log = LoggerFactory.getLogger(Helper.class);

  private ElasticsearchContainer elasticsearchContainer;
  public ElasticsearchConnectionSettings connectionSettings;

  private KafkaContainer kafka;
  public String kafkaBootstrapServers;

  public Helper() {
  }

  public void startContainers() {
    elasticsearchContainer =
        new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.2");
    elasticsearchContainer.start();
    connectionSettings = ElasticsearchConnectionSettings.create("http://" + elasticsearchContainer.getHttpHostAddress());

    kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.1")); // contains Kafka 2.4.x
    kafka.start();
    kafkaBootstrapServers = kafka.getBootstrapServers();
  }

  public void stopContainers() {
    kafka.stop();
    elasticsearchContainer.stop();
  }

  CompletionStage<Done> writeToKafka(String topic, List<Movie> movies, ActorSystem<?> actorSystem) {
    ProducerSettings<Integer, String> kafkaProducerSettings =
        ProducerSettings.create(toClassic(actorSystem), new IntegerSerializer(), new StringSerializer())
            .withBootstrapServers(kafkaBootstrapServers);

    CompletionStage<Done> producing =
        Source.from(movies)
            .map(
                movie -> {
                  log.debug("producing {}", movie);
                  String json = JsonMappers.movieWriter.writeValueAsString(movie);
                  return new ProducerRecord<>(topic, movie.id, json);
                })
            .runWith(Producer.plainSink(kafkaProducerSettings), actorSystem);
    producing.thenAccept(s -> log.info("Producing finished"));
    return producing;
  }

  CompletionStage<List<Movie>> readFromElasticsearch(String indexName, ActorSystem<?> actorSystem) {
    CompletionStage<List<Movie>> reading =
        ElasticsearchSource.typed(
                ElasticsearchParams.V7(indexName),
                "{\"match_all\": {}}",
                ElasticsearchSourceSettings.create(connectionSettings),
                Movie.class)
            .map(readResult -> readResult.source())
            .runWith(Sink.seq(), actorSystem);
    reading.thenAccept(
        non -> {
          log.info("Reading finished");
        });
    return reading;
  }


}
