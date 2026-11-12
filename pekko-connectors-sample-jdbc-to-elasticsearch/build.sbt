lazy val base = project
  .in(file("."))
  .aggregate(
    common,
    step_001_complete
  ).settings(CommonSettings.commonSettings: _*)

lazy val common = project.settings(CommonSettings.commonSettings: _*)

lazy val step_001_complete = project
  .settings(CommonSettings.commonSettings: _*)
  .dependsOn(common % "test->test;compile->compile")

ThisBuild / javafmtFormatterCompatibleJavaVersion := 17
ThisBuild / javafmtSortImports := false

addCommandAlias("applyCodeStyle", "scalafmtAll; scalafmtSbt; javafmtAll")
addCommandAlias("checkCodeStyle", "scalafmtCheckAll; scalafmtSbtCheck; javafmtCheckAll")
