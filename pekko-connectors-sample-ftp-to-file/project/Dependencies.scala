import sbt._

object Dependencies {
  val scalaVer = "2.13.7"
  // #deps
  val PekkoVersion = "1.0.0"
  val PekkoConnectorsVersion = "0.0.0+129-1853d802-SNAPSHOT" // #TODO: Change to release version

  // #deps

  val dependencies = List(
  // #deps
    "org.apache.pekko" %% "pekko-connectors-ftp" % PekkoConnectorsVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
    // #deps
    // Playground file system and FTP server
    // https://mina.apache.org/ftpserver-project/downloads.html
    "org.apache.ftpserver" % "ftpserver-core" % "1.1.1", // ApacheV2
    "com.google.jimfs" % "jimfs" % "1.1", // ApacheV2
    // Logging
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}
