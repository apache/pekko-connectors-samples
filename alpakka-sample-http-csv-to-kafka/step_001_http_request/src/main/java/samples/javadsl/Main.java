/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.javadsl;

import org.apache.pekko.Done;
import org.apache.pekko.actor.ActorSystem;
import org.apache.pekko.http.javadsl.Http;
import org.apache.pekko.http.javadsl.model.HttpRequest;
import org.apache.pekko.http.javadsl.model.MediaRanges;
import org.apache.pekko.http.javadsl.model.headers.Accept;
import org.apache.pekko.stream.javadsl.Sink;
import org.apache.pekko.stream.javadsl.Source;

import java.util.Collections;
import java.util.concurrent.CompletionStage;

public class Main {

    public static void main(String[] args) throws Exception {
        Main me = new Main();
        me.run();
    }

    final HttpRequest httpRequest =
            HttpRequest.create(
                    "https://www.nasdaq.com/screening/companies-by-name.aspx?exchange=NASDAQ&render=download")
                    .withHeaders(Collections.singletonList(Accept.create(MediaRanges.ALL_TEXT)));

    private void run() throws Exception {
        ActorSystem system = ActorSystem.create();
        Http http = Http.get(system);

        CompletionStage<Done> completion =
                Source.single(httpRequest) // : HttpRequest
                        .mapAsync(1, http::singleRequest) // : HttpResponse
                        .runWith(Sink.foreach(response -> System.out.println(response)), system);

        completion
                .thenAccept(
                        done -> {
                            System.out.println("Done!");
                            system.terminate();
                        });
    }
}
