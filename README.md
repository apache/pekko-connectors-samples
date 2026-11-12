# Apache Pekko Connectors samples

Samples for [Apache Pekko Connectors](https://pekko.apache.org/docs/pekko-connectors/current/).

The samples should run with Java 8 or above. If you are building the docs using [Paradox](https://developer.lightbend.com/docs/paradox/current/index.html)
then you will need to use Java 11.

## Status

The samples now use Pekko libs instead of Akka/Alpakka. If you find any issues, please raise an issue or a Pull Request.

There is still some work to do on the docs but the snapshot docs should be usable. See https://nightlies.apache.org/pekko/docs/pekko-connectors-samples/main-snapshot/docs/

## Organize Scala imports

Each sbt example containing Scala sources includes Scalafix. Run
`sbt sortImports` from that example directory to organize main and test imports
and remove unused imports. Each example carries its own configuration so it
continues to work when copied outside this repository.

This uses Scalafix 0.14.10 and the OrganizeImports grouping from
[apache/pekko#3342](https://github.com/apache/pekko/pull/3342). Relative imports
remain unexpanded and adjacent to their root import.

Import regions used as documentation snippets are explicitly excluded from this
rule so their marker boundaries and rendered contents stay unchanged.
