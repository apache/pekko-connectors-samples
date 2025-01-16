import sbt._

object Dependencies {
  val scalaVer = "2.13.16"
  // #deps
  val PekkoVersion = "1.1.3"

  val PekkoConnectorsVersion = "1.1.0"

  // #deps

  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-connectors-elasticsearch" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-connectors-slick" % PekkoConnectorsVersion,
    // for JSON in Scala
    "io.spray" %% "spray-json" % "1.3.6",
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.3.15",
    // #deps
    "com.h2database" % "h2" % "2.2.224",
    "org.testcontainers" % "elasticsearch" % "1.17.6",
    "org.testcontainers" % "postgresql" % "1.17.6"
  )
}
