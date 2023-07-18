import sbt._

object Dependencies {
  val scalaVer = "2.13.7"
  // #dependencies
  val ScalaTestVersion = "3.1.4"
  val PekkoVersion = "1.0.0"
  val PekkoHttpVersion = "0.0.0+4469-fb6a5426-SNAPSHOT" // #TODO: Change to release version
  val PekkoConnectorsVersion = "0.0.0+131-79ec6fa6-SNAPSHOT" // #TODO: Change to release version
  val PekkoConnectorsKafkaVersion = "0.0.0+1761-2291eac2-SNAPSHOT" // #TODO: Change to release version

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

    "org.testcontainers" % "kafka" % "1.18.3",
    
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
  // #dependencies
}
