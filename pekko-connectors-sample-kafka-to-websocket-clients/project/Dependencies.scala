import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #deps
  val PekkoVersion = "2.6.19"
  val AkkaHttpVersion = "10.1.12"
  val AlpakkaKafkaVersion = "3.0.1"
  // #deps
  val dependencies = List(
    // #deps
    "com.typesafe.akka" %% "akka-stream" % PekkoVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream-kafka" % AlpakkaKafkaVersion,

    // Logging
    "com.typesafe.akka" %% "akka-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13",
    // #deps

    "org.testcontainers" % "kafka" % "1.14.3",

    "com.typesafe.akka" %% "akka-stream-testkit" % PekkoVersion % Test,
    "com.google.guava" % "guava" % "28.2-jre" % Test,
    "junit" % "junit" % "4.13" % Test,

  )
}
