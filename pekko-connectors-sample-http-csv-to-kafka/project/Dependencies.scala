import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #dependencies
  val ScalaTestVersion = "3.2.17"
  val PekkoVersion = "1.0.2"
  val PekkoHttpVersion = "1.0.0"
  val PekkoConnectorsVersion = "1.0.1"
  val PekkoConnectorsKafkaVersion = "1.0.0"

  val dependencies = List(
    "org.apache.pekko" %% "pekko-connectors-csv" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-connectors-kafka" % PekkoConnectorsKafkaVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
    // Used from Scala
    "org.apache.pekko" %% "pekko-http-spray-json" % PekkoHttpVersion,
    // Used from Java
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.14.3",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.14.3",

    "org.testcontainers" % "kafka" % "1.17.3",
    
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13"
  )
  // #dependencies
}
