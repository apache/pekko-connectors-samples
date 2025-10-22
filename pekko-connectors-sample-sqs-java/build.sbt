organization := "org.apache.pekko"
name := "pekko-connectors-samples-sqs-java"

ThisBuild / scalaVersion := "2.13.17"

val PekkoVersion = "1.2.1"
val PekkoConnectorsVersion = "1.2.0"
val jacksonVersion = "2.20.0"

libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
  "org.apache.pekko" %% "pekko-connectors-sqs" % PekkoConnectorsVersion,
  "com.github.pjfanning" %% "aws-spi-pekko-http" % "0.1.0",
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "ch.qos.logback" % "logback-classic" % "1.3.15",
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))

javacOptions ++= Seq(
  "-Xlint:deprecation"
)
