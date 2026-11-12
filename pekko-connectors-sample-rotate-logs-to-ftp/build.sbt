organization := "org.apache.pekko"
name := "pekko-connectors-sample-rotate-logs-to-ftp"
version := "1.0.0"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies
javacOptions += "-Xlint:unchecked"

ThisBuild / javafmtFormatterCompatibleJavaVersion := 17
ThisBuild / javafmtSortImports := false

addCommandAlias("applyCodeStyle", "scalafmtAll; scalafmtSbt; javafmtAll")
addCommandAlias("checkCodeStyle", "scalafmtCheckAll; scalafmtSbtCheck; javafmtCheckAll")
