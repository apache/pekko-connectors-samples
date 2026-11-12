# Apache Pekko Connectors samples

Samples for [Apache Pekko Connectors](https://pekko.apache.org/docs/pekko-connectors/current/).

The samples should run with Java 8 or above. If you are building the docs using [Paradox](https://developer.lightbend.com/docs/paradox/current/index.html)
then you will need to use Java 11.

## Status

The samples now use Pekko libs instead of Akka/Alpakka. If you find any issues, please raise an issue or a Pull Request.

There is still some work to do on the docs but the snapshot docs should be usable. See https://nightlies.apache.org/pekko/docs/pekko-connectors-samples/main-snapshot/docs/

## Code formatting

Each sbt example uses Apache Pekko's Scalafmt configuration. Examples containing
Java sources also use sbt-java-formatter with the JDK 17 formatter and import
sorting disabled. From an example directory, use `sbt applyCodeStyle` to format
sources and build files, and `sbt checkCodeStyle` to check them. Use JDK 17 or later
for Java formatting. Managed/generated sources are not formatting inputs.
Maven-only examples retain their existing build tooling.

The sbt builds use sbt 1.13.1 because sbt-scalafmt 2.6.2 requires sbt 1.12.9
or later. This tooling update does not upgrade the examples' runtime libraries.

Scala 2 examples retain the Scala 2 dialect and disable Scala 3 syntax rewrites;
formatting does not change their compiler language mode.
