# Apache Pekko Connectors sample

## Read from a Kafka topic and push the data to connected websocket clients

Clients may connect via websockets and will receive data read from a Kafka topic. The websockets are implemented in @extref[Akka HTTP](akka-http:) and [Pekko-Connectors Kafka](pekko-connectors-kafka:) subscribes to the Kafka topic.

Browse the sources at @link:[Github](https://github.com/apache/incubator-pekko-connectors-samples/tree/main/pekko-connectors-sample-kafka-to-websocket-clients) { open=new }.

To try out this project clone @link:[the Pekko-Connectors Samples repository](https://github.com/apache/incubator-pekko-connectors-samples) { open=new } and find it in the `pekko-connectors-sample-kafka-to-websocket-clients` directory.

## Running

The sample spawns a test Kafka server with docker. 

```
sbt "runMain samples.javadsl.Main"
```

You can connect to ws://127.0.0.1/events to receive messages over websockets. E.g. Using [`websocat`](https://github.com/vi/websocat) as a simple WS client.

To listen to events coming in on the websocket use `websocat` to connect to the `/events` endpoint.

```
websocat -v ws://127.0.0.1:8081/events 
```

You can use `curl` to post messages to the topic.

```
curl http://127.0.0.1:8081/push?value=message
``` 
