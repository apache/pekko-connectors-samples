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
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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

    private Map<String, String> cleanseCsvData(Map<String, ByteString> map) {
        Map<String, String> out = new HashMap<>(map.size());
        map.forEach(
                (key, value) -> {
                    if (!key.isEmpty()) out.put(key, value.utf8String());
                });
        return out;
    }

    private final JsonFactory jsonFactory = new JsonFactory();

    private String toJson(Map<String, String> map) throws Exception {
        StringWriter sw = new StringWriter();
        JsonGenerator generator = jsonFactory.createGenerator(sw);
        generator.writeStartObject();
        map.forEach(
                (key, value) -> {
                    try {
                        generator.writeStringField(key, value);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        generator.writeEndObject();
        generator.close();
        return sw.toString();
    }

    private void run() throws Exception {
        ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "pekko-connectors-samples");
        Http http = Http.get(toClassic(system));

        CompletionStage<Done> completion =
                Source.single(httpRequest) // : HttpRequest
                        .mapAsync(1, http::singleRequest) // : HttpResponse
                        .flatMapConcat(this::extractEntityData) // : ByteString
                        .via(CsvParsing.lineScanner()) // : List<ByteString>
                        .via(CsvToMap.toMap()) // : Map<String, ByteString>
                        .map(this::cleanseCsvData) // : Map<String, String>
                        .map(this::toJson) // : String
                        .runWith(Sink.foreach(System.out::println), system);

        completion
                .thenAccept(
                        done -> {
                            System.out.println("Done!");
                            system.terminate();
                        });
    }
}
