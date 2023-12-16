/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.scaladsl

// #sample
import org.apache.pekko.Done
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.model._
import org.apache.pekko.stream.connectors.jms.scaladsl.{ JmsConsumer, JmsConsumerControl }
import org.apache.pekko.stream.connectors.jms.JmsConsumerSettings
import org.apache.pekko.stream.scaladsl.{ Keep, Sink, Source }
import org.apache.pekko.util.ByteString

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

// #sample
import playground.{ ActiveMqBroker, WebServer }

object JmsToHttpGet extends JmsSampleBase with App {

  WebServer.start()
  ActiveMqBroker.start()

  val connectionFactory = ActiveMqBroker.createConnectionFactory
  enqueue(connectionFactory)("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")

  // format: off
  // #sample
  val jmsSource: Source[String, JmsConsumerControl] =                                 // (1)
  JmsConsumer.textSource(
    JmsConsumerSettings(system, connectionFactory).withBufferSize(10).withQueue("test")
  )

  val (runningSource, finished): (JmsConsumerControl, Future[Done]) =
    jmsSource                                                   //: String
      .map(ByteString(_))                                       //: ByteString   (2)
      .map { bs =>
      HttpRequest(uri = Uri("http://localhost:8080/hello"),   //: HttpRequest  (3)
        entity = HttpEntity(bs))
    }
      .mapAsyncUnordered(4)(Http(system).singleRequest(_))            //: HttpResponse (4)
      .toMat(Sink.foreach(println))(Keep.both)                  //               (5)
      .run()
  // #sample
  // format: on
  finished.foreach(_ => println("stream finished"))

  wait(5.seconds)
  runningSource.shutdown()
  system.terminate()
  for {
    _ <- system.whenTerminated
    _ <- WebServer.stop()
    _ <- ActiveMqBroker.stop()
  } ()

}
