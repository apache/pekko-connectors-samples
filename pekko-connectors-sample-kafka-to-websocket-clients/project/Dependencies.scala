import sbt._

object Dependencies {
  val scalaVer = "2.13.16"
  // #deps
  val PekkoVersion = "1.1.3"
  val PekkoConnectorsKafkaVersion = "1.0.0"
  val PekkoHttpVersion = "1.1.0"

  // #deps
  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
    "org.apache.pekko" %% "pekko-connectors-kafka" % PekkoConnectorsKafkaVersion,

    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.3.15",
    // #deps

    "org.testcontainers" % "kafka" % "1.17.6",
    "org.apache.pekko" %% "pekko-stream-testkit" % PekkoVersion,
    "com.google.guava" % "guava" % "28.2-jre" % Test,
    "junit" % "junit" % "4.13.2" % Test
  )
}
