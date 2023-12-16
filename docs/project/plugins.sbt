addSbtPlugin("com.lightbend.akka" % "sbt-paradox-akka" % "0.44")
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")
addSbtPlugin("com.lightbend.sbt" % "sbt-publish-rsync" % "0.2")

dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"
libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.6"
