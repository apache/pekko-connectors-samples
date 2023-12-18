dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"
libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.6"

resolvers += Resolver.ApacheMavenSnapshotsRepo

addSbtPlugin("org.apache.pekko" % "pekko-sbt-paradox" % "1.0.0+8-1d350942-SNAPSHOT")
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")
