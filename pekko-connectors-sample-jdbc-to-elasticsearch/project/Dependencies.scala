import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #deps
  val pekkoVersion = "1.0.2"

  val pekkoConnectorVersion = "1.0.1"

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
    "ch.qos.logback" % "logback-classic" % "1.2.13",
    // #deps
    "com.h2database" % "h2" % "2.1.214",
    "org.testcontainers" % "elasticsearch" % "1.17.6",
    "org.testcontainers" % "postgresql" % "1.17.6"
  )
}
