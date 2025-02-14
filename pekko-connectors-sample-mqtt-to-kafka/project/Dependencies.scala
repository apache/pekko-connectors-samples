import sbt._

object Dependencies {
  val scalaVer = "2.13.16"
  // #deps
  val PekkoVersion = "1.1.3"
  val PekkoConnectorsVersion = "1.1.0"
  val PekkoConnectorsKafkaVersion = "1.1.0"
  val JacksonDatabindVersion = "2.17.3"
  // #deps

  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-connectors-mqtt" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-connectors-kafka" % PekkoConnectorsKafkaVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
    "org.scala-lang.modules" %% "scala-collection-compat" % "2.1.4",
    // JSON
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % JacksonDatabindVersion,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % JacksonDatabindVersion,
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % JacksonDatabindVersion,
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.3.15",
    "org.testcontainers" % "kafka" % "1.17.6"
    // #deps
  )
}
