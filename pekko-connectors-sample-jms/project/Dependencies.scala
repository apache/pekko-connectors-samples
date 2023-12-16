import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #deps
  val pekkoVersion = "1.0.1"
  val pekkoHttpVersion = "1.0.0"
  val pekkoConnectorVersion = "1.0.1"

  // #deps

  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-connectors-jms" % pekkoConnectorVersion,
    "org.apache.pekko" %% "pekko-http" % pekkoHttpVersion,
    "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
    "org.apache.pekko" %% "pekko-actor" % pekkoVersion,
    // https://github.com/javaee/javax.jms
    "javax.jms" % "jms" % "1.1", // CDDL Version 1.1
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % pekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    // #deps
    // http://activemq.apache.org/download.html
    "org.apache.activemq" % "activemq-all" % "5.16.0" exclude("log4j", "log4j") exclude("org.slf4j", "slf4j-log4j12") // ApacheV2
  )
}
