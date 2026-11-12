import sbt.Keys._

name := "pekko-connectors-sample-file-to-elasticsearch"
organization := "samples"
version := "1.0.0"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies
ThisBuild / javafmtFormatterCompatibleJavaVersion := 17
ThisBuild / javafmtSortImports := false

addCommandAlias("applyCodeStyle", "scalafmtAll; scalafmtSbt; javafmtAll")
addCommandAlias("checkCodeStyle", "scalafmtCheckAll; scalafmtSbtCheck; javafmtCheckAll")
