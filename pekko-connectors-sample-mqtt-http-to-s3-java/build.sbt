organization := "org.apache.pekko"
name := "pekko-connectors-samples-mqtt-http-to-s3-java"

ThisBuild / scalaVersion := "2.13.17"

val PekkoVersion = "1.2.1"
val PekkoHttpVersion = "1.2.0"
val PekkoConnectorsVersion = "1.2.0"

libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
  "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
  "org.apache.pekko" %% "pekko-connectors-s3" % PekkoConnectorsVersion,
  "org.apache.pekko" %% "pekko-connectors-mqtt" % PekkoConnectorsVersion,
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.20.0",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.20.0",
  "ch.qos.logback" % "logback-classic" % "1.3.15"
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))

javacOptions ++= Seq(
  "-Xlint:deprecation"
)
