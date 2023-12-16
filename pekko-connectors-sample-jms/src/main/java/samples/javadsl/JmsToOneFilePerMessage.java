/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.javadsl;

// #sample

import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.japi.Pair;
import org.apache.pekko.stream.KillSwitch;
import org.apache.pekko.stream.connectors.jms.JmsConsumerSettings;
import org.apache.pekko.stream.connectors.jms.JmsProducerSettings;
import org.apache.pekko.stream.connectors.jms.javadsl.JmsConsumer;
import org.apache.pekko.stream.connectors.jms.javadsl.JmsConsumerControl;
import org.apache.pekko.stream.connectors.jms.javadsl.JmsProducer;
import org.apache.pekko.stream.javadsl.FileIO;
import org.apache.pekko.stream.javadsl.Keep;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.pekko.util.ByteString;
import playground.ActiveMqBroker;

import javax.jms.ConnectionFactory;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import scala.concurrent.ExecutionContext;

// #sample

public class JmsToOneFilePerMessage {

  public static void main(String[] args) throws Exception {
    JmsToOneFilePerMessage me = new JmsToOneFilePerMessage();
    me.run();
  }

  private final ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "JmsToOneFilePerMessage");
  private final ExecutionContext ec = system.executionContext();

  private void enqueue(ConnectionFactory connectionFactory, String... msgs) {
    Sink<String, ?> jmsSink =
        JmsProducer.textSink(
            JmsProducerSettings.create(system, connectionFactory).withQueue("test"));
    Source.from(Arrays.asList(msgs)).runWith(jmsSink, system);
  }

  private void run() throws Exception {
    ActiveMqBroker activeMqBroker = new ActiveMqBroker();
    activeMqBroker.start();

    ConnectionFactory connectionFactory = activeMqBroker.createConnectionFactory();
    enqueue(connectionFactory, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
    // #sample

    Source<String, JmsConsumerControl> jmsConsumer = // (1)
        JmsConsumer.textSource(
            JmsConsumerSettings.create(system, connectionFactory).withQueue("test"));

    int parallelism = 5;
    Pair<JmsConsumerControl, CompletionStage<Done>> pair =
        jmsConsumer // : String
            .map(ByteString::fromString) // : ByteString             (2)
            .zipWithIndex() // : Pair<ByteString, Long> (3)
            .mapAsyncUnordered(
                parallelism,
                (in) -> {
                  ByteString byteString = in.first();
                  Long number = in.second();
                  return Source // (4)
                      .single(byteString)
                      .runWith(
                          FileIO.toPath(Paths.get("target/out-" + number + ".txt")), system);
                }) // : IoResult
            .toMat(Sink.ignore(), Keep.both())
            .run(system);

    // #sample

    KillSwitch runningSource = pair.first();
    CompletionStage<Done> streamCompletion = pair.second();

    Thread.sleep(2 * 1000);

    runningSource.shutdown();
    streamCompletion.thenAccept(res -> system.terminate());
    system.getWhenTerminated().thenCompose(t -> activeMqBroker.stopCs(ec)).toCompletableFuture().get(5, TimeUnit.SECONDS);
  }
}
