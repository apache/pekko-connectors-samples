/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.scaladsl

import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.stream.connectors.elasticsearch._
import org.apache.pekko.stream.connectors.elasticsearch.scaladsl.ElasticsearchSource
import org.apache.pekko.stream.connectors.slick.scaladsl.{ Slick, SlickSession }
import org.apache.pekko.stream.scaladsl.{ Sink, Source }
import org.slf4j.LoggerFactory
import org.testcontainers.elasticsearch.ElasticsearchContainer
import samples.scaladsl.Main.Movie

import scala.collection.immutable
import scala.concurrent.duration._
import scala.concurrent.{ Await, Future }

trait Helper {

  final val log = LoggerFactory.getLogger(getClass)

  // TestContainers: start Elasticsearch in Docker
  val elasticsearchContainer = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.2")
  elasticsearchContainer.start()
  val elasticsearchAddress: String = "http://" + elasticsearchContainer.getHttpHostAddress

  def readFromElasticsearch(indexName: String)(implicit actorSystem: ActorSystem[_]): Future[immutable.Seq[Movie]] = {
    val reading = ElasticsearchSource
      .typed[Movie](ElasticsearchParams.V7(indexName), """{"match_all": {}}""",
        ElasticsearchSourceSettings(ElasticsearchConnectionSettings(elasticsearchAddress)))
      .map(_.source)
      .runWith(Sink.seq)
    reading.foreach(_ => log.info("Reading finished"))(actorSystem.executionContext)
    reading
  }

  def stopContainers() = {
    elasticsearchContainer.stop()
  }
}

object Helper {
  def populateDataForTable()(implicit session: SlickSession, actorSystem: ActorSystem[_]) = {

    import session.profile.api._

    // Drop table if already exists
    val dropTableFut =
      sqlu"""drop table if exists MOVIE"""

    // Create movie table
    val createTableFut =
      sqlu"""create table MOVIE (ID INT PRIMARY KEY, TITLE varchar, GENRE varchar, GROSS numeric(10,2))"""

    Await.result(session.db.run(dropTableFut), 10.seconds)
    Await.result(session.db.run(createTableFut), 10.seconds)

    // A class just for organizing the data before using it in the insert clause.  Could have been insertFut with a Tuple too
    case class MovieInsert(id: Int, title: String, genre: String, gross: Double)

    val movies = List(
      MovieInsert(1, "Rogue One", "Adventure", 3.032),
      MovieInsert(2, "Beauty and the Beast", "Musical", 2.795),
      MovieInsert(3, "Wonder Woman", "Action", 2.744),
      MovieInsert(4, "Guardians of the Galaxy", "Action", 2.568),
      MovieInsert(5, "Moana", "Musical", 2.493),
      MovieInsert(6, "Spider-Man", "Action", 1.784))

    Source(movies)
      .via(
        Slick.flow(movie =>
          sqlu"INSERT INTO MOVIE VALUES (${movie.id}, ${movie.title}, ${movie.genre}, ${movie.gross})"))
      .runWith(Sink.ignore)
  }

}
