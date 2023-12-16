/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.scaladsl

import org.apache.pekko.Done
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.stream.connectors.jms.scaladsl.JmsProducer
import org.apache.pekko.stream.connectors.jms.JmsProducerSettings
import org.apache.pekko.stream.scaladsl.{ Sink, Source }

import javax.jms.ConnectionFactory
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration._

class JmsSampleBase {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "JmsSample")
  implicit val executionContext: ExecutionContext = system.executionContext

  def wait(duration: FiniteDuration): Unit = Thread.sleep(duration.toMillis)

  def terminateActorSystem(): Unit = {
    system.terminate()
    Await.result(system.whenTerminated, 1.seconds)
  }

  def enqueue(connectionFactory: ConnectionFactory)(msgs: String*): Unit = {
    val jmsSink: Sink[String, Future[Done]] =
      JmsProducer.textSink(
        JmsProducerSettings(system, connectionFactory).withQueue("test"))
    Source(msgs.toList).runWith(jmsSink)
  }
}
