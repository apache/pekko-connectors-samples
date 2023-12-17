organization := "org.apache.pekko"
name := "pekko-connectors-samples-mqtt-http-to-s3-java"

ThisBuild / scalaVersion := "2.13.12"

val PekkoVersion = "1.0.2"
val PekkoHttpVersion = "1.0.0"
val PekkoConnectorsVersion = "1.0.1"

libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
  "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
  "org.apache.pekko" %% "pekko-connectors-s3" % PekkoConnectorsVersion,
  "org.apache.pekko" %% "pekko-connectors-mqtt" % "1.0.1",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.14.2",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.14.2",
  "ch.qos.logback" % "logback-classic" % "1.2.13"
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))

javacOptions ++= Seq(
  "-Xlint:deprecation"
)
