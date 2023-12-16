### Read text messages from JMS queue and send to web socket

- listens to the JMS queue "test" receiving `String`s (1),
- configures a web socket flow to localhost (2),
- converts incoming data to a @scala[@scaladoc[ws.TextMessage](org.apache.pekko.http.scaladsl.model.ws.TextMessage)]@java[@scaladoc[org.apache.pekko.http.javadsl.model.ws.TextMessage](org.apache.pekko.http.javadsl.model.ws.TextMessage)] (3),
- pass the message via the web socket flow (4),
- convert the (potentially chunked) web socket reply to a `String` (5),
- prefix the `String` (6),
- end the stream by writing the values to standard out (7).

Scala
: @@snip [snip](/src/main/scala/samples/scaladsl/JmsToWebSocket.scala) { #sample }

Java
: @@snip [snip](/src/main/java/samples/javadsl/JmsToWebSocket.java) { #sample }
