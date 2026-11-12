# Apache Pekko Connectors sample

## Receive JMS messages

These examples show small usages of @extref[Pekko-Connector JMS](pekko-connectors:jms).

Browse the sources at @link:[Github](https://github.com/apache/pekko-connectors-samples/tree/main/pekko-connectors-sample-jms) { open=new }.

To try out this project clone @link:[the Pekko-Connector Samples repository](https://github.com/apache/pekko-connectors-samples) { open=new } and find it in the `pekko-connectors-sample-jms` directory.

### Organize Scala imports

Run `sbt sortImports` to organize Scala imports with Scalafix and remove unused
imports. The command enables SemanticDB and unused-import diagnostics for this sbt session
and processes both
main and test sources. Java sources are not rewritten.
