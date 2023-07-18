/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.javadsl;

import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.http.javadsl.Http;
import org.apache.pekko.http.javadsl.model.HttpRequest;
import org.apache.pekko.http.javadsl.model.HttpResponse;
import org.apache.pekko.http.javadsl.model.MediaRanges;
import org.apache.pekko.http.javadsl.model.StatusCodes;
import org.apache.pekko.http.javadsl.model.headers.Accept;
import org.apache.pekko.stream.connectors.csv.javadsl.CsvParsing;
import org.apache.pekko.stream.connectors.csv.javadsl.CsvToMap;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;
import org.apache.pekko.util.ByteString;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

import static org.apache.pekko.actor.typed.javadsl.Adapter.toClassic;

public class Main {

    public static void main(String[] args) throws Exception {
        Main me = new Main();
        me.run();
    }

    final HttpRequest httpRequest =
            HttpRequest.create(
                    "https://www.nasdaq.com/screening/companies-by-name.aspx?exchange=NASDAQ&render=download")
                    .withHeaders(Collections.singletonList(Accept.create(MediaRanges.ALL_TEXT)));

    private Source<ByteString, ?> extractEntityData(HttpResponse httpResponse) {
        if (httpResponse.status() == StatusCodes.OK) {
            return httpResponse.entity().getDataBytes();
        } else {
            return Source.failed(new RuntimeException("illegal response " + httpResponse));
        }
    }

    private void run() throws Exception {
        ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "pekko-connectors-samples");
        Http http = Http.get(toClassic(system));

        CompletionStage<Done> completion =
                Source.single(httpRequest) // : HttpRequest
                        .mapAsync(1, http::singleRequest) // : HttpResponse
                        .flatMapConcat(this::extractEntityData) // : ByteString
                        .via(CsvParsing.lineScanner()) // : List<ByteString>
                        .via(CsvToMap.toMapAsStrings(StandardCharsets.UTF_8)) // : Map<String, String>
                        .runWith(Sink.foreach(map -> map.entrySet().stream().forEach(System.out::println)), system);

        completion
                .thenAccept(
                        done -> {
                            System.out.println("Done!");
                            system.terminate();
                        });
    }
}
