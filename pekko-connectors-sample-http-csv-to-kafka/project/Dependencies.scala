import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #dependencies
  val ScalaTestVersion = "3.1.4"
  val PekkoVersion = "2.6.19"
  val AkkaHttpVersion = "10.1.12"
  val PekkoConnectorsVersion = "4.0.0"
  val AlpakkaKafkaVersion = "3.0.1"

  val dependencies = List(
    "com.lightbend.akka" %% "akka-stream-alpakka-csv" % PekkoConnectorsVersion,
    "com.typesafe.akka" %% "akka-stream-kafka" % AlpakkaKafkaVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % PekkoVersion,
    "com.typesafe.akka" %% "akka-stream" % PekkoVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    // Used from Scala
    "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
    // Used from Java
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.11.4",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.11.4",

    "org.testcontainers" % "kafka" % "1.14.3",
    
    "com.typesafe.akka" %% "akka-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13"
  )
  // #dependencies
}
