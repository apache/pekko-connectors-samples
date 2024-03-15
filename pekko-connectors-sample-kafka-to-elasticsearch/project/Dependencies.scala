import sbt._

object Dependencies {
  val scalaVer = "2.13.13"
  // #deps
  val PekkoVersion = "1.0.2"
  val PekkoConnectorsVersion = "1.0.2"
  val PekkoConnectorsKafkaVersion = "1.0.0"

  // #deps

  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-connectors-elasticsearch" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-connectors-kafka" % PekkoConnectorsKafkaVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
    // for JSON in Scala
    "io.spray" %% "spray-json" % "1.3.6",
    // for JSON in Java
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.14.3",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.14.3",
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13",
    // #deps
    "org.testcontainers" % "elasticsearch" % "1.17.6",
    "org.testcontainers" % "kafka" % "1.17.6")
}
