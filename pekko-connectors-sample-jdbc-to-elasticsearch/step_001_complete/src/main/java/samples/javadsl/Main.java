/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

/*
 * Copyright (C) 2016-2019 Lightbend Inc. <http://www.lightbend.com>
 */

package samples.javadsl;

// #imports

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletionStage;

import org.apache.pekko.Done;
import org.apache.pekko.actor.typed.ActorSystem;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.stream.connectors.elasticsearch.ElasticsearchConnectionSettings;
import org.apache.pekko.stream.connectors.elasticsearch.ElasticsearchParams;
import org.apache.pekko.stream.connectors.elasticsearch.ElasticsearchWriteSettings;
import org.apache.pekko.stream.connectors.elasticsearch.WriteMessage;
import org.apache.pekko.stream.connectors.elasticsearch.javadsl.ElasticsearchSink;
import org.apache.pekko.stream.connectors.slick.javadsl.Slick;
import org.apache.pekko.stream.connectors.slick.javadsl.SlickRow;
import org.apache.pekko.stream.connectors.slick.javadsl.SlickSession;
import org.apache.pekko.stream.connectors.slick.javadsl.SlickSession$;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import samples.scaladsl.Helper;

import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

public class Main {

    public static void main(String[] args) {
        Main me = new Main();
        me.run();
    }

    // #data-class
    public static class Movie {

        public final int id;
        public final String title;
        public final String genre;
        public final double gross;

        @JsonCreator
        public Movie(
            @JsonProperty("id") int id,
            @JsonProperty("title") String title,
            @JsonProperty("genre") String genre,
            @JsonProperty("gross") double gross) {
            this.id = id;
            this.title = title;
            this.genre = genre;
            this.gross = gross;
        }
    }

    // #data-class

    void run() {
        // Testcontainers: start Elasticsearch in Docker
        ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch-oss:7.10.2");
        elasticsearchContainer.start();
        String elasticsearchAddress = "http://" + elasticsearchContainer.getHttpHostAddress();
        // #sample
        ActorSystem<Object> system = ActorSystem.create(Behaviors.empty(), "alpakka-sample");
        // #sample
        // #slick-setup
        SlickSession session = SlickSession$.MODULE$.forConfig("slick-h2-mem");
        system.getWhenTerminated().thenAccept(done -> session.close());
        // #slick-setup
        Helper.populateDataForTable(session, system);

        // #es-setup
        ElasticsearchConnectionSettings connectionSettings = ElasticsearchConnectionSettings.create(elasticsearchAddress);
        // #es-setup

        // #data-class
        final ObjectMapper objectToJsonMapper = new ObjectMapper();
        // #data-class

        // #sample
        final CompletionStage<Done> done =
            Slick.source(
                    session,
                    "SELECT * FROM MOVIE",
                    (SlickRow row) ->
                        new Movie(row.nextInt(), row.nextString(), row.nextString(), row.nextDouble()))
                .map(movie -> WriteMessage.createIndexMessage(String.valueOf(movie.id), movie))
                .runWith(
                    ElasticsearchSink.create(
                        ElasticsearchParams.V7("movie"),
                        ElasticsearchWriteSettings.create(connectionSettings),
                        objectToJsonMapper),
                    system);
        // #sample

        done.thenRunAsync(
            () -> {
                elasticsearchContainer.stop();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // ignored
                }
                system.terminate();
                try {
                    Await.result(system.whenTerminated(), Duration.create("10s"));
                } catch (Exception e) {
                    // ignored
                }
            });
    }
}
