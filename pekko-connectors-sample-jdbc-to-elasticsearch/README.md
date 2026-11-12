# Apache Pekko Connectors sample

## Read from a database and publish to Elasticsearch

This example uses @extref[Pekko-Connectors Slick](pekko-connectors:slick.html) to read from a database, and stores the data in Elasticsearch.

Browse the sources at @link:[Github](https://github.com/apache/pekko-connectors-samples/tree/main/pekko-connectors-sample-jdbc-to-elasticsearch) { open=new }.

To try out this project clone @link:[the Pekko-Connectors Samples repository](https://github.com/apache/pekko-connectors-samples) { open=new } and find it in the `pekko-connectors-sample-jdbc-to-elasticsearch` directory.

### Organize Scala imports

Run `sbt sortImports` to organize Scala imports with Scalafix and remove unused
imports. The command enables SemanticDB and unused-import diagnostics for this sbt session
and processes both
main and test sources. Java sources are not rewritten.
