package samples.triggereddownload;

import static org.apache.pekko.actor.typed.javadsl.Adapter.toClassic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.http.javadsl.Http;
import org.apache.pekko.http.javadsl.model.ContentTypes;
import org.apache.pekko.http.javadsl.model.HttpRequest;
import org.apache.pekko.http.javadsl.model.Uri;
import org.apache.pekko.stream.connectors.mqtt.MqttConnectionSettings;
import org.apache.pekko.stream.connectors.mqtt.MqttQoS;
import org.apache.pekko.stream.connectors.mqtt.MqttSubscriptions;
import org.apache.pekko.stream.connectors.mqtt.javadsl.MqttSource;
import org.apache.pekko.stream.connectors.s3.javadsl.S3;
import org.apache.pekko.stream.javadsl.Source;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

  final ActorSystem<Void> system;
  final Http http;

  public static void main(String[] args) throws Exception {
    Main me = new Main();
    me.run();
  }

  public Main() {
    system = ActorSystem.create(Behaviors.empty(), "MqttHttpToS3");
    http = Http.get(toClassic(system));
  }

  final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
  final ObjectReader downloadCommandReader = mapper.readerFor(DownloadCommand.class);

  final String mqttBroker = "tcp://localhost:1883";
  // Remember to set up topic in MQTT server's acl config
  final String topic = "downloads/trigger";
  final String s3Bucket = "pekko.connectors.samples";

  private String createS3BucketKey(DownloadCommand info) {
    return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        + Uri.create(info.url).getPathString().replace("/", "-");
  }

  void run() throws Exception {
    final MqttConnectionSettings mqttConnectionSettings =
        MqttConnectionSettings
            .create(
                mqttBroker,
                "upload-control",
                new MemoryPersistence()
            );

    MqttSubscriptions mqttSubscriptions =
        MqttSubscriptions.create(topic, MqttQoS.atLeastOnce());

    MqttSource
        .atMostOnce(mqttConnectionSettings, mqttSubscriptions, 8)
        .map(m -> m.payload().utf8String())
        .<DownloadCommand>map(downloadCommandReader::readValue)
        .mapAsync(4, info -> {
              String s3BucketKey = createS3BucketKey(info);
              return Source.single(info.url)
                  .map(HttpRequest::GET)
                  .mapAsync(1, http::singleRequest)
                  .flatMapConcat(httpResponse -> httpResponse.entity().getDataBytes())
                  .runWith(S3.multipartUpload(s3Bucket, s3BucketKey, ContentTypes.TEXT_HTML_UTF8), system);
            }
        )
        .runForeach(System.out::println, system)
        .exceptionally(e -> {
          e.printStackTrace();
          return Done.done();
        });
  }

}
