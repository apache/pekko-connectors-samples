organization := "com.lightbend.akka.samples"
name := "pekko-connectors-sample-ftp-to-file"
version := "0.0.1"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies

// #TODO: Remove these lines ones Pekko Connectors have 1.0.0
resolvers += "Apache Snapshots" at "https://repository.apache.org/content/repositories/snapshots/"