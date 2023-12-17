import sbt.Keys._

name := "pekko-connectors-sample-kafka-to-websocket-clients"
organization := "org.apache.pekko"
version := "1.0.1"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies

fork / run := true
connectInput / run := true
