addSbtPlugin("org.apache.pekko" % "pekko-sbt-paradox" % "0.0.0+55-2e9e22a8-SNAPSHOT") // #TODO: user release version
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")
addSbtPlugin("com.lightbend.sbt" % "sbt-publish-rsync" % "0.2")
resolvers += Resolver.jcenterRepo

libraryDependencies += "org.scalameta" %% "scalameta" % "4.4.6"
resolvers += "Apache Snapshots" at "https://repository.apache.org/content/repositories/snapshots/"