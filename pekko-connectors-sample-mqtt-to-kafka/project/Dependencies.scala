import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #deps
  val PekkoVersion = "2.6.19"
  val PekkoConnectorsVersion = "4.0.0"
  val AlpakkaKafkaVersion = "3.0.1"
  val JacksonDatabindVersion = "2.11.4"

  // #deps

  val dependencies = List(
  // #deps
    "com.lightbend.akka" %% "akka-stream-alpakka-mqtt" % PekkoConnectorsVersion,
    "com.typesafe.akka" %% "akka-stream-kafka" % AlpakkaKafkaVersion,
    "com.typesafe.akka" %% "akka-stream" % PekkoVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % PekkoVersion,
    "com.typesafe.akka" %% "akka-actor" % PekkoVersion,
    "org.scala-lang.modules" %% "scala-collection-compat" % "2.1.4",
    // JSON
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % JacksonDatabindVersion,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % JacksonDatabindVersion,
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % JacksonDatabindVersion,
    // Logging
    "com.typesafe.akka" %% "akka-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13",
  // #deps
    "org.testcontainers" % "kafka" % "1.14.1"
  )
}
