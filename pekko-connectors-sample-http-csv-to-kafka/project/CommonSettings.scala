import sbt.Keys._
import sbt._
import sbtstudent.AdditionalSettings

object CommonSettings {
  lazy val commonSettings = Seq(
    organization := "com.lightbend.training",
    version := "1.3.0",
    scalaVersion := Dependencies.scalaVer,
    scalacOptions ++= CompileOptions.compileOptions,
    Compile / unmanagedSourceDirectories := List((Compile / scalaSource).value, (Compile / javaSource).value),
    Test / unmanagedSourceDirectories := List((Compile / scalaSource).value, (Compile / javaSource).value),
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-v"),
    Test / parallelExecution := false,
    Test / logBuffered := false,
    ThisBuild / parallelExecution := false,
    GlobalScope / parallelExecution := false,
    Test / fork := true,
    libraryDependencies ++= Dependencies.dependencies,

    // #TODO: Remove these lines ones Pekko Connectors have 1.0.0
    resolvers += "Apache Snapshots" at "https://repository.apache.org/content/repositories/snapshots/",
    libraryDependencySchemes += "org.apache.pekko" %% "pekko-stream" % VersionScheme.Always
  ) ++
    AdditionalSettings.initialCmdsConsole ++
    AdditionalSettings.initialCmdsTestConsole ++
    AdditionalSettings.cmdAliases
}

