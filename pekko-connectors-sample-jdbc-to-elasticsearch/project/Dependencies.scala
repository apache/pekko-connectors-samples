/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

import sbt._

object Dependencies {
  val scalaVer = "2.13.8"

  val pekkoVersion = "1.0.1"

  val pekkoConnectorVersion = "1.0.1+12-db689989-SNAPSHOT"

  // #deps

  val dependencies = List(
  // #deps
    "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
    "org.apache.pekko" %% "pekko-connectors-elasticsearch" % pekkoConnectorVersion,
    "org.apache.pekko" %% "pekko-connectors-slick" % pekkoConnectorVersion,
    // for JSON in Scala
    "io.spray" %% "spray-json" % "1.3.6",
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % pekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.4.7",
    // #deps
    "com.h2database" % "h2" % "2.1.214",
    "org.testcontainers" % "elasticsearch" % "1.17.6",
    "org.testcontainers" % "postgresql" % "1.17.6"
  )
}
