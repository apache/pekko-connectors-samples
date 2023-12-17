package sample.triggereddownload;

import static org.apache.pekko.actor.typed.javadsl.Adapter.toClassic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.http.javadsl.Http;
import org.apache.pekko.stream.connectors.mqtt.MqttConnectionSettings;
import org.apache.pekko.stream.connectors.mqtt.MqttMessage;
import org.apache.pekko.stream.connectors.mqtt.MqttQoS;
import org.apache.pekko.stream.connectors.mqtt.javadsl.MqttSink;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.pekko.util.ByteString;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletionStage;

public class PublishDataToMqtt {

  final ActorSystem<Void> system;
  final Http http;

  public static void main(String[] args) throws Exception {
    PublishDataToMqtt me = new PublishDataToMqtt();
    me.run();
  }

  public PublishDataToMqtt() {
    system = ActorSystem.create(Behaviors.empty(), "PublishDataToMqTT");
    http = Http.get(toClassic(system));
  }

  final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
  final ObjectWriter downloadCommandWriter = mapper.writerFor(DownloadCommand.class);


  void run() throws Exception {

    final MqttConnectionSettings connectionSettings =
        MqttConnectionSettings.create(
            "tcp://localhost:1883", "test-java-client", new MemoryPersistence());
    Sink<MqttMessage, CompletionStage<Done>> mqttSink =
        MqttSink.create(connectionSettings.withClientId("source-test/sink"), MqttQoS.atLeastOnce());

    DownloadCommand command = new DownloadCommand(Instant.now(), "https://doc.akka.io/docs/connectors/current/s3.html");
    MqttMessage message = MqttMessage.create("downloads/trigger", ByteString.fromString(downloadCommandWriter.writeValueAsString(command)));

    Source.tick(Duration.ofSeconds(5), Duration.ofSeconds(30), message).runWith(mqttSink, system);
  }
}
