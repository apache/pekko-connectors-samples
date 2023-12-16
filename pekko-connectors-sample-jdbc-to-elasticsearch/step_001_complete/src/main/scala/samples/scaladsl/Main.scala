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

// #imports

import org.apache.pekko.Done
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.stream.connectors.elasticsearch._
import org.apache.pekko.stream.connectors.elasticsearch.scaladsl.ElasticsearchSink
import org.apache.pekko.stream.connectors.slick.scaladsl.{ Slick, SlickSession }
import spray.json.DefaultJsonProtocol.{ jsonFormat4, _ }
import spray.json.JsonFormat

import scala.concurrent.duration._
import scala.concurrent.{ Await, ExecutionContext, Future }

object Main extends App with Helper {
  implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "alpakka-sample")
  implicit val executionContext: ExecutionContext = actorSystem.executionContext

  def wait(duration: FiniteDuration): Unit = Thread.sleep(duration.toMillis)

  def terminateActorSystem(): Unit = {
    actorSystem.terminate()
    Await.result(actorSystem.whenTerminated, 1.seconds)
  }

  // #slick-setup

  implicit val session = SlickSession.forConfig("slick-h2-mem") // (1)
  actorSystem.whenTerminated.map(_ => session.close())

  import session.profile.api._
  class Movies(tag: Tag) extends Table[(Int, String, String, Double)](tag, "MOVIE") { // (2)
    def id = column[Int]("ID")
    def title = column[String]("TITLE")
    def genre = column[String]("GENRE")
    def gross = column[Double]("GROSS")

    override def * = (id, title, genre, gross)
  }
  // #slick-setup
  Await.result(Helper.populateDataForTable(), 2.seconds)

  // #data-class
  case class Movie(id: Int, title: String, genre: String, gross: Double)

  implicit val format: JsonFormat[Movie] = jsonFormat4(Movie)
  // #data-class

  // #es-setup
  val connection = ElasticsearchConnectionSettings(elasticsearchAddress)
  // #es-setup

  // #sample
  val done: Future[Done] =
    Slick
      .source(TableQuery[Movies].result)
      .map {
        case (id, genre, title, gross) => Movie(id, genre, title, gross)
      }
      .map(movie => WriteMessage.createIndexMessage(movie.id.toString, movie))
      .runWith(ElasticsearchSink.create[Movie](ElasticsearchParams.V7("movie"),
        ElasticsearchWriteSettings(connection)))

  // #sample
  done.failed.foreach(exception => log.error("failure", exception))
  done.onComplete(_ => stopContainers())
  wait(10.seconds)
  terminateActorSystem()

}
