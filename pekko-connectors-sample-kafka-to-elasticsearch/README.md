# Apache Pekko Connectors sample

## Read from a Kafka topic and publish to Elasticsearch

This example uses @extref[Alpakka Kafka](alpakka-kafka:) to subscribe to a Kafka topic, parses JSON into a data class and stores the object in Elasticsearch. After storing the Kafka offset is committed back to Kafka. This gives at-least-once semantics.

Browse the sources at @link:[Github](https://github.com/apache/incubator-pekko-connectors-samples/tree/main/pekko-connectors-sample-kafka-to-elasticsearch) { open=new }.

To try out this project clone @link:[the Pekko-Connectors Samples repository](https://github.com/apache/incubator-pekko-connectors-samples) { open=new } and find it in the `alpakka-sample-kafka-to-elasticsearch` directory.
