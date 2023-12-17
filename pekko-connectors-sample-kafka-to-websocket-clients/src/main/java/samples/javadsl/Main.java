/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.javadsl;

// #imports

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.pekko.Done;
import org.apache.pekko.NotUsed;
import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.http.javadsl.ConnectHttp;
import org.apache.pekko.http.javadsl.Http;
import org.apache.pekko.http.javadsl.ServerBinding;
import org.apache.pekko.http.javadsl.model.HttpRequest;
import org.apache.pekko.http.javadsl.model.HttpResponse;
import org.apache.pekko.http.javadsl.model.ws.Message;
import org.apache.pekko.http.javadsl.model.ws.TextMessage;
import org.apache.pekko.http.javadsl.server.AllDirectives;
import org.apache.pekko.http.javadsl.server.Route;
import org.apache.pekko.japi.Pair;
import org.apache.pekko.kafka.ConsumerSettings;
import org.apache.pekko.kafka.Subscriptions;
import org.apache.pekko.kafka.javadsl.Consumer;
import org.apache.pekko.stream.Materializer;
import org.apache.pekko.stream.OverflowStrategy;
import org.apache.pekko.stream.SystemMaterializer;
import org.apache.pekko.stream.javadsl.BroadcastHub;
import org.apache.pekko.stream.javadsl.Flow;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
// #imports

public class Main extends AllDirectives {

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  private final Helper helper;
  private final String kafkaBootstrapServers;

  private final String topic = "message-topic";
  private final String groupId = "docs-group";

  private ActorSystem actorSystem;
  private Materializer materializer;

  public Main(Helper helper) {
    this.kafkaBootstrapServers = helper.kafkaBootstrapServers;
    this.helper = helper;
  }

  public static void main(String[] args) throws Exception {
    Helper helper = new Helper();
    helper.startContainers();
    Main main = new Main(helper);
    main.run();
    helper.stopContainers();
  }

  private void run() throws Exception {
    actorSystem = ActorSystem.create("KafkaToWebSocket");
    materializer = SystemMaterializer.get(actorSystem).materializer();
    Http http = Http.get(actorSystem);

    // #websocket-handler
    Flow<Message, Message, ?> webSocketHandler =
        Flow.fromSinkAndSource(
            Sink.ignore(),
            topicSource()
                // decouple clients from each other: if a client is too slow and more than 1000 elements to be sent to
                // the client queue up here, we fail this client
                .buffer(1000, OverflowStrategy.fail())
                .via(addIndexFlow())
                .map(TextMessage::create));
    // #websocket-handler

    final Flow<HttpRequest, HttpResponse, ?> routeFlow = createRoute(webSocketHandler).flow(actorSystem, materializer);
    final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
        ConnectHttp.toHost("localhost", 8081), materializer);

    binding.toCompletableFuture().get(10, TimeUnit.SECONDS);

    System.out.println("Server online at http://localhost:8081/\nPress RETURN to stop...");
    System.in.read(); // let it run until user presses return
  }

  public Flow<String, String, NotUsed> addIndexFlow() {
    final Pair<Integer, String> seed = Pair.create(0, "start");
    return Flow.of(String.class)
        .scan(seed, (acc, message) -> {
          Integer index = acc.first();
          return Pair.create(index + 1, String.format("index: %s, message: %s", index, message));
        })
        .filterNot(p -> p == seed)
        .map(Pair::second);
  }

  // #routes
  private Route createRoute(Flow<Message, Message, ?> webSocketHandler) {
    return concat(
        path("events", () -> handleWebSocketMessages(webSocketHandler)),
        path("push", () -> parameter("value", v -> {
          CompletionStage<Done> written = helper.writeToKafka(topic, v, actorSystem);
          return onSuccess(written, done -> complete("Ok"));
        }))
    );
  }
  // #routes

  // #kafka-to-broadcast
  private Source<String, ?> topicSource() {
    ConsumerSettings<Integer, String> kafkaConsumerSettings =
        ConsumerSettings.create(actorSystem, new IntegerDeserializer(), new StringDeserializer())
            .withBootstrapServers(kafkaBootstrapServers)
            .withGroupId(groupId)
            .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
            .withStopTimeout(Duration.ofSeconds(5));

    return
        Consumer.plainSource(kafkaConsumerSettings, Subscriptions.topics(topic))
            .map(ConsumerRecord::value)
            // using a broadcast hub here, ensures that all websocket clients will use the same
            // consumer
            .runWith(BroadcastHub.of(String.class), materializer);
  }
  // #kafka-to-broadcast
}
