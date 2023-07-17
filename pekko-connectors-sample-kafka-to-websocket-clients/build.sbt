import sbt.Keys._

name := "pekko-connectors-sample-kafka-to-websocket-clients"
organization := "com.lightbend.akka"
version := "1.3.0"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies

fork / run := true
connectInput / run := true
