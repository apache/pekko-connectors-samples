import sbt._

object Dependencies {
  val scalaVer = "2.13.12"
  // #deps
  val PekkoVersion = "2.6.19"
  val PekkoConnectorsVersion = "4.0.0"

  // #deps

  val dependencies = List(
  // #deps
    "com.typesafe.akka" %% "akka-stream" % PekkoVersion,
    "com.typesafe.akka" %% "akka-actor-typed" % PekkoVersion,
    "com.typesafe.akka" %% "akka-actor" % PekkoVersion,
    "com.lightbend.akka" %% "akka-stream-alpakka-file" % PekkoConnectorsVersion,
    "com.lightbend.akka" %% "akka-stream-alpakka-ftp" % PekkoConnectorsVersion,
    // #deps
    // Playground file system and FTP server
    // https://mina.apache.org/ftpserver-project/downloads.html
    "org.apache.ftpserver" % "ftpserver-core" % "1.1.1", // ApacheV2
    "org.apache.sshd" % "sshd-scp" % "2.5.1", // ApacheV2
    "org.apache.sshd" % "sshd-sftp" % "2.5.1", // ApacheV2
    "com.google.jimfs" % "jimfs" % "1.1", // ApacheV2
    // Logging
    "com.typesafe.akka" %% "akka-slf4j" % PekkoVersion,
    "ch.qos.logback" % "logback-classic" % "1.2.13"
  )
}
