import sbt._

object Dependencies {
  val scalaVer = "2.13.13"
  // #deps
  val PekkoVersion = "1.0.2"
  val PekkoHttpVersion = "1.0.1"
  val PekkoConnectorsVersion = "1.0.2"

  // #deps

  val dependencies = List(
    // #deps
    "org.apache.pekko" %% "pekko-connectors-jms" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
    // https://github.com/javaee/javax.jms
    "javax.jms" % "jms" % "1.1", // CDDL Version 1.1
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13",
    // #deps
    // http://activemq.apache.org/download.html
    "org.apache.activemq" % "activemq-all" % "5.16.7" exclude("log4j", "log4j") exclude("org.slf4j", "slf4j-log4j12") // ApacheV2
  )
}
