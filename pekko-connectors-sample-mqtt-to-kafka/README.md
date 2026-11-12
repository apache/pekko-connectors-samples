# Apache Pekko Connectors sample

## Read from an MQTT topic, group messages and publish to Kafka

Subscribe to an MQTT topic with @extref[Pekko-Connectors MQTT](pekko-connectors:/mqtt.html), group a few values and publish the aggregate to a Kafka topic with @extref[Pekko-Connectors Kafka](pekko-connectors-kafka:).

Browse the sources at @link:[Github](https://github.com/apache/pekko-connectors-samples/tree/main/pekko-connectors-sample-mqtt-to-kafka) { open=new }.

To try out this project clone @link:[the Pekko-Connectors Samples repository](https://github.com/apache/pekko-connectors-samples) { open=new } and find it in the `pekko-connectors-sample-mqtt-to-kafka` directory.

### Organize Scala imports

Run `sbt sortImports` to organize Scala imports with Scalafix and remove unused
imports. The command enables SemanticDB and unused-import diagnostics for this sbt session
and processes both
main and test sources. Java sources are not rewritten.
