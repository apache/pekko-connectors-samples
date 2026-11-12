organization := "org.apache.pekko"
version := "1.0.0"
scalaVersion := Dependencies.scalaVer
libraryDependencies ++= Dependencies.dependencies
// Having JBoss as a first resolver is a workaround for https://github.com/coursier/coursier/issues/200
externalResolvers := ("jboss".at("https://repository.jboss.org/nexus/content/groups/public")) +: externalResolvers.value

ThisBuild / javafmtFormatterCompatibleJavaVersion := 17
ThisBuild / javafmtSortImports := false

addCommandAlias("applyCodeStyle", "scalafmtAll; scalafmtSbt; javafmtAll")
addCommandAlias("checkCodeStyle", "scalafmtCheckAll; scalafmtSbtCheck; javafmtCheckAll")
