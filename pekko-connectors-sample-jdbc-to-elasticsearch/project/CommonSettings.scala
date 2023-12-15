/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * license agreements; and to You under the Apache License, version 2.0:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * This file is part of the Apache Pekko project, which was derived from Akka.
 */

import sbt.Keys._
import sbt._

object CommonSettings {
  lazy val commonSettings = Seq(
    organization := "org.apache.pekko",
    version := "1.0.1",
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
    libraryDependencies ++= Dependencies.dependencies
  )
}
