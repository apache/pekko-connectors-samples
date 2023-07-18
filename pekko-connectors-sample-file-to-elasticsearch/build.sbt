import sbt.Keys._

name := "pekko-connectors-sample-file-to-elasticsearch"
organization := "samples"
version := "1.0.0"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies

// #TODO: Remove these lines ones Pekko Connectors have 1.0.0
resolvers += "Apache Snapshots" at "https://repository.apache.org/content/repositories/snapshots/"
libraryDependencySchemes += "org.apache.pekko" %% "pekko-stream" % VersionScheme.Always

