addSbtPlugin("com.lightbend.akka" % "sbt-paradox-akka" % "0.44")
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")

dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"
libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.6"
