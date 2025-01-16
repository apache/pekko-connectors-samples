import sbt._

object Dependencies {
  val scalaVer = "2.13.16"
  // #deps
  val PekkoVersion = "1.1.3"
  val PekkoConnectorsVersion = "1.1.0"
  val jacksonVersion = "2.17.3"
  // #deps
  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-connectors-file" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-connectors-elasticsearch" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
    "org.scala-lang.modules" %% "scala-java8-compat" % "1.0.2",
    // for JSON in Scala
    "io.spray" %% "spray-json" % "1.3.6",
    // for JSON in Java
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % jacksonVersion,
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonVersion,
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.3.15",
    // #deps
    "org.testcontainers" % "elasticsearch" % "1.17.3"
  )
}
