/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.javadsl;

// #sample

import static org.apache.pekko.actor.typed.javadsl.Adapter.*;

import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.http.javadsl.Http;
import org.apache.pekko.http.javadsl.model.HttpRequest;
import org.apache.pekko.japi.Pair;
import org.apache.pekko.stream.connectors.jms.JmsConsumerSettings;
import org.apache.pekko.stream.connectors.jms.JmsProducerSettings;
import org.apache.pekko.stream.connectors.jms.javadsl.JmsConsumer;
import org.apache.pekko.stream.connectors.jms.javadsl.JmsConsumerControl;
import org.apache.pekko.stream.connectors.jms.javadsl.JmsProducer;
import org.apache.pekko.stream.javadsl.Keep;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.pekko.util.ByteString;
import playground.ActiveMqBroker;
import playground.WebServer;

import scala.concurrent.ExecutionContext;

import javax.jms.ConnectionFactory;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

// #sample

public class JmsToHttpGet {

  public static void main(String[] args) throws Exception {
    JmsToHttpGet me = new JmsToHttpGet();
    me.run();
  }

  private final ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "JmsToHttpGet");
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

    WebServer webserver = new WebServer();
    webserver.start("localhost", 8080);

    ConnectionFactory connectionFactory = activeMqBroker.createConnectionFactory();
    enqueue(connectionFactory, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k");
    // #sample

    final Http http = Http.get(toClassic(system));

    Source<String, JmsConsumerControl> jmsSource = // (1)
        JmsConsumer.textSource(
            JmsConsumerSettings.create(system, connectionFactory).withQueue("test"));

    int parallelism = 4;
    Pair<JmsConsumerControl, CompletionStage<Done>> pair =
        jmsSource // : String
            .map(ByteString::fromString) // : ByteString   (2)
            .map(
                bs ->
                    HttpRequest.create("http://localhost:8080/hello")
                        .withEntity(bs)) // : HttpRequest  (3)
            .mapAsyncUnordered(parallelism, http::singleRequest) // : HttpResponse (4)
            .toMat(Sink.foreach(System.out::println), Keep.both()) //               (5)
            .run(system);
    // #sample
    Thread.sleep(5 * 1000);
    JmsConsumerControl runningSource = pair.first();
    CompletionStage<Done> streamCompletion = pair.second();

    runningSource.shutdown();
    streamCompletion.thenAccept(res -> system.terminate());
    system
        .getWhenTerminated()
        .thenCompose(
            t -> {
              webserver.stop();
              return activeMqBroker.stopCs(ec);
            }).toCompletableFuture().get(5, TimeUnit.SECONDS);
  }
}
