organization := "org.apache.pekko"
name := "pekko-connectors-samples-sqs-java"

ThisBuild / scalaVersion := "2.13.12"

val PekkoVersion = "1.0.2"
val PekkoConnectorsVersion = "1.0.2"
val jacksonVersion = "2.14.3"

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
  "ch.qos.logback" % "logback-classic" % "1.2.13",
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))

javacOptions ++= Seq(
  "-Xlint:deprecation"
)
