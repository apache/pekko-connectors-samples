/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples

import org.apache.pekko.Done
import org.apache.pekko.actor._
import org.apache.pekko.http.scaladsl._
import org.apache.pekko.http.scaladsl.model.StatusCodes._
import org.apache.pekko.http.scaladsl.model.headers.Accept
import org.apache.pekko.http.scaladsl.model.{ HttpRequest, HttpResponse, MediaRanges }
import org.apache.pekko.stream._
import org.apache.pekko.stream.scaladsl.{ Sink, Source }
import org.apache.pekko.util.ByteString

import scala.concurrent.Future

object Main extends App {

  implicit val actorSystem = ActorSystem("alpakka-samples")

  import actorSystem.dispatcher

  val httpRequest = HttpRequest(uri = "https://www.nasdaq.com/screening/companies-by-name.aspx?exchange=NASDAQ&render=download")
    .withHeaders(Accept(MediaRanges.`text/*`))

  val future: Future[Done] =
    Source
      .single(httpRequest) //: HttpRequest
      .mapAsync(1)(Http().singleRequest(_)) //: HttpResponse
      .runWith(Sink.foreach(println))

  future.map { _ =>
    println("Done!")
    actorSystem.terminate()
  }

}
