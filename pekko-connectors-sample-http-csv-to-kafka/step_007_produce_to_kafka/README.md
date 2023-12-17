# Step 7: Produce to Kafka

### Description

[Testcontainers](https://www.testcontainers.org/) starts a Kafka broker in Docker. 

@extref:[Pekko-Connectors Kafka](pekko-connectors-kafka:producer.html) producer settings specify the broker address and the data types for Kafka's key and value.

@scaladoc:[Producer.plainSink](org.apache.pekko.kafka.scaladsl.Producer$) sends the `ProducerRecord`s stream elements to the specified Kafka topic.
