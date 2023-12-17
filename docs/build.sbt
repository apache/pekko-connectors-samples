
ThisBuild / scalaVersion := "2.13.12"

enablePlugins(AkkaParadoxPlugin, ParadoxSitePlugin)

name := "Pekko Connectors Samples"
previewFixedPort := Some(8085)
scmInfo := Some(ScmInfo(url("https://github.com/apache/incubator-pekko-connectors-samples"), "git@github.com:apache/incubator-pekko-connectors-samples.git"))
homepage := Some(url("https://github.com/apache/incubator-pekko-connectors-samples"))
description := "Example solutions for Enterprise Integrations using Pekko Connectors and Reactive Streams."
version := {
  val time = java.time.LocalDateTime.now().withSecond(0).withNano(0)
  java.time.format.DateTimeFormatter.ISO_DATE.format(time) + " " + java.time.format.DateTimeFormatter.ISO_TIME.format(time)
}
isSnapshot := true

val FtpToFile = config("ftp-to-file")
ParadoxPlugin.paradoxSettings(FtpToFile)
ParadoxSitePlugin.paradoxSettings(FtpToFile)
AkkaParadoxPlugin.akkaParadoxSettings(FtpToFile)
FtpToFile / siteSubdirName := FtpToFile.name
FtpToFile / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${FtpToFile.name}" / "docs" / "src" / "main" / "paradox"
FtpToFile / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${FtpToFile.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${FtpToFile.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${FtpToFile.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.FtpToFile.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.FtpToFile.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.FtpToFile.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.FtpToFile.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.FtpToFile.PekkoVersion}/%s",
)
FtpToFile / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val HttpCsvToKafka = config("http-csv-to-kafka")
ParadoxPlugin.paradoxSettings(HttpCsvToKafka)
ParadoxSitePlugin.paradoxSettings(HttpCsvToKafka)
AkkaParadoxPlugin.akkaParadoxSettings(HttpCsvToKafka)
HttpCsvToKafka / siteSubdirName := HttpCsvToKafka.name
HttpCsvToKafka / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${HttpCsvToKafka.name}" / "docs" / "src" / "main" / "paradox"
HttpCsvToKafka / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${HttpCsvToKafka.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${HttpCsvToKafka.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${HttpCsvToKafka.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.akka.stream.alpakka.base_url" -> s"https://doc.akka.io/api/alpakka/${Dependencies.HttpCsvToKafka.PekkoConnectorsVersion}",
  "javadoc.akka.base_url" -> "",
  "extref.alpakka.base_url" -> s"https://doc.akka.io/docs/alpakka/${Dependencies.HttpCsvToKafka.PekkoConnectorsVersion}/%s",
  // Pekko Connectors Kafka
  "scaladoc.akka.kafka.base_url" -> s"https://doc.akka.io/api/pekko-connectors-kafka/${Dependencies.HttpCsvToKafka.PekkoConnectorsKafkaVersion}",
  "javadoc.akka.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://doc.akka.io/docs/pekko-connectors-kafka/${Dependencies.HttpCsvToKafka.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.akka.base_url" -> s"https://doc.akka.io/api/akka/${Dependencies.HttpCsvToKafka.PekkoVersion}",
  "javadoc.akka.base_url" -> s"https://doc.akka.io/japi/akka/${Dependencies.HttpCsvToKafka.PekkoVersion}",
  "extref.akka.base_url" -> s"https://doc.akka.io/docs/akka/${Dependencies.HttpCsvToKafka.PekkoVersion}/%s",
  // Pekko HTTP
  "scaladoc.akka.http.base_url" -> s"https://doc.akka.io/api/akka-http/${Dependencies.HttpCsvToKafka.AkkaHttpVersion}",
  "javadoc.akka.http.base_url" -> s"https://doc.akka.io/japi/akka-http/${Dependencies.HttpCsvToKafka.AkkaHttpVersion}",
  "extref.akka-http.base_url" -> s"https://doc.akka.io/docs/akka-http/${Dependencies.HttpCsvToKafka.AkkaHttpVersion}/%s",
)
HttpCsvToKafka / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val JdbcToElasticsearch = config("jdbc-to-elasticsearch")
ParadoxPlugin.paradoxSettings(JdbcToElasticsearch)
ParadoxSitePlugin.paradoxSettings(JdbcToElasticsearch)
AkkaParadoxPlugin.akkaParadoxSettings(JdbcToElasticsearch)
JdbcToElasticsearch / siteSubdirName := JdbcToElasticsearch.name
JdbcToElasticsearch / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${JdbcToElasticsearch.name}" / "docs" / "src" / "main" / "paradox"
JdbcToElasticsearch / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${JdbcToElasticsearch.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${JdbcToElasticsearch.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${JdbcToElasticsearch.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  "scaladoc.pekko.stream.pekko-connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.JdbcToElasticsearch.PekkoConnectorsVersion}",
  "javadoc.pekko.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.JdbcToElasticsearch.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.JdbcToElasticsearch.PekkoVersion}",
  "javadoc.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.JdbcToElasticsearch.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.JdbcToElasticsearch.PekkoVersion}/%s",
)
JdbcToElasticsearch / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val Jms = config("jms")
ParadoxPlugin.paradoxSettings(Jms)
ParadoxSitePlugin.paradoxSettings(Jms)
AkkaParadoxPlugin.akkaParadoxSettings(Jms)
Jms / siteSubdirName := Jms.name
Jms / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${Jms.name}" / "docs" / "src" / "main" / "paradox"
Jms / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${Jms.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${Jms.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${Jms.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.pekko.stream.pekko-connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.Jms.PekkoConnectorsVersion}",
  "javadoc.pekko.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.Jms.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.Jms.PekkoVersion}",
  "javadoc.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.Jms.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.Jms.PekkoVersion}/%s",
  // Pekko HTTP
  "scaladoc.org.apache.pekko.http.base_url" -> s"https://pekko.apache.org/api/pekko-http/${Dependencies.Jms.PekkoHttpVersion}",
  "javadoc.org.apache.pekko.http.base_url" -> s"https://pekko.apache.org/japi/pekko-http/${Dependencies.Jms.PekkoHttpVersion}",
  "extref.org.apache.pekko-http.base_url" -> s"https://pekko.apache.org/docs/pekko-http/${Dependencies.Jms.PekkoHttpVersion}/%s",
)
Jms / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))


val KafkaToElasticsearch = config("kafka-to-elasticsearch")
ParadoxPlugin.paradoxSettings(KafkaToElasticsearch)
ParadoxSitePlugin.paradoxSettings(KafkaToElasticsearch)
AkkaParadoxPlugin.akkaParadoxSettings(KafkaToElasticsearch)
KafkaToElasticsearch / siteSubdirName := KafkaToElasticsearch.name
KafkaToElasticsearch / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${KafkaToElasticsearch.name}" / "docs" / "src" / "main" / "paradox"
KafkaToElasticsearch / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${KafkaToElasticsearch.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${KafkaToElasticsearch.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${KafkaToElasticsearch.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.KafkaToElasticsearch.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.KafkaToElasticsearch.PekkoConnectorsVersion}/%s",
  // Pekko Connectors Kafka
  "scaladoc.org.apache.pekko.kafka.base_url" -> s"https://pekko.apache.org/api/pekko-connectors-kafka/${Dependencies.KafkaToElasticsearch.PekkoConnectorsKafkaVersion}",
  "javadoc.org.apache.pekko.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors-kafka/${Dependencies.KafkaToElasticsearch.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.KafkaToElasticsearch.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.KafkaToElasticsearch.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.KafkaToElasticsearch.PekkoVersion}/%s",
)
KafkaToElasticsearch / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val KafkaToWebsocketClients = config("kafka-to-websocket-clients")
ParadoxPlugin.paradoxSettings(KafkaToWebsocketClients)
ParadoxSitePlugin.paradoxSettings(KafkaToWebsocketClients)
AkkaParadoxPlugin.akkaParadoxSettings(KafkaToWebsocketClients)
KafkaToWebsocketClients / siteSubdirName := KafkaToWebsocketClients.name
KafkaToWebsocketClients / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${KafkaToWebsocketClients.name}" / "docs" / "src" / "main" / "paradox"
KafkaToWebsocketClients / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${KafkaToWebsocketClients.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${KafkaToWebsocketClients.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${KafkaToWebsocketClients.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
//  "scaladoc.akka.stream.alpakka.base_url" -> s"https://doc.akka.io/api/alpakka/${Dependencies.KafkaToWebsocketClients.PekkoConnectorsVersion}",
//  "javadoc.akka.base_url" -> "",
//  "extref.alpakka.base_url" -> s"https://doc.akka.io/docs/alpakka/${Dependencies.KafkaToWebsocketClients.PekkoConnectorsVersion}/%s",
  // Pekko Connectors Kafka
  "scaladoc.akka.kafka.base_url" -> s"https://doc.akka.io/api/pekko-connectors-kafka/${Dependencies.KafkaToWebsocketClients.PekkoConnectorsKafkaVersion}",
  "javadoc.akka.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://doc.akka.io/docs/pekko-connectors-kafka/${Dependencies.KafkaToWebsocketClients.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.akka.base_url" -> s"https://doc.akka.io/api/akka/${Dependencies.KafkaToWebsocketClients.PekkoVersion}",
  "javadoc.akka.base_url" -> s"https://doc.akka.io/japi/akka/${Dependencies.KafkaToWebsocketClients.PekkoVersion}",
  "extref.akka.base_url" -> s"https://doc.akka.io/docs/akka/${Dependencies.KafkaToWebsocketClients.PekkoVersion}/%s",
  // Pekko HTTP
  "scaladoc.akka.http.base_url" -> s"https://doc.akka.io/api/akka-http/${Dependencies.KafkaToWebsocketClients.AkkaHttpVersion}",
  "javadoc.akka.http.base_url" -> s"https://doc.akka.io/japi/akka-http/${Dependencies.KafkaToWebsocketClients.AkkaHttpVersion}",
  "extref.akka-http.base_url" -> s"https://doc.akka.io/docs/akka-http/${Dependencies.KafkaToWebsocketClients.AkkaHttpVersion}/%s",
)
KafkaToWebsocketClients / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val MqttToKafka = config("mqtt-to-kafka")
ParadoxPlugin.paradoxSettings(MqttToKafka)
ParadoxSitePlugin.paradoxSettings(MqttToKafka)
AkkaParadoxPlugin.akkaParadoxSettings(MqttToKafka)
MqttToKafka / siteSubdirName := MqttToKafka.name
MqttToKafka / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${MqttToKafka.name}" / "docs" / "src" / "main" / "paradox"
MqttToKafka / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${MqttToKafka.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${MqttToKafka.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${MqttToKafka.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.akka.stream.alpakka.base_url" -> s"https://doc.akka.io/api/alpakka/${Dependencies.MqttToKafka.PekkoConnectorsVersion}",
  "javadoc.akka.base_url" -> "",
  "extref.alpakka.base_url" -> s"https://doc.akka.io/docs/alpakka/${Dependencies.MqttToKafka.PekkoConnectorsVersion}/%s",
  // Pekko Connectors Kafka
  "scaladoc.akka.kafka.base_url" -> s"https://doc.akka.io/api/pekko-connectors-kafka/${Dependencies.MqttToKafka.PekkoConnectorsKafkaVersion}",
  "javadoc.akka.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://doc.akka.io/docs/pekko-connectors-kafka/${Dependencies.MqttToKafka.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.akka.base_url" -> s"https://doc.akka.io/api/akka/${Dependencies.MqttToKafka.PekkoVersion}",
  "javadoc.akka.base_url" -> s"https://doc.akka.io/japi/akka/${Dependencies.MqttToKafka.PekkoVersion}",
  "extref.akka.base_url" -> s"https://doc.akka.io/docs/akka/${Dependencies.MqttToKafka.PekkoVersion}/%s",
)
MqttToKafka / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))


val FileToElasticsearch = config("file-to-elasticsearch")
ParadoxPlugin.paradoxSettings(FileToElasticsearch)
ParadoxSitePlugin.paradoxSettings(FileToElasticsearch)
AkkaParadoxPlugin.akkaParadoxSettings(FileToElasticsearch)
FileToElasticsearch / siteSubdirName := FileToElasticsearch.name
FileToElasticsearch / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${FileToElasticsearch.name}" / "docs" / "src" / "main" / "paradox"
FileToElasticsearch / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${FileToElasticsearch.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${FileToElasticsearch.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${FileToElasticsearch.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.FileToElasticsearch.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.FileToElasticsearch.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.FileToElasticsearch.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.FileToElasticsearch.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.FileToElasticsearch.PekkoVersion}/%s",
)
FileToElasticsearch / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val RotateLogsToFtp = config("rotate-logs-to-ftp")
ParadoxPlugin.paradoxSettings(RotateLogsToFtp)
ParadoxSitePlugin.paradoxSettings(RotateLogsToFtp)
AkkaParadoxPlugin.akkaParadoxSettings(RotateLogsToFtp)
RotateLogsToFtp / siteSubdirName := RotateLogsToFtp.name
RotateLogsToFtp / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${RotateLogsToFtp.name}" / "docs" / "src" / "main" / "paradox"
RotateLogsToFtp / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${RotateLogsToFtp.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${RotateLogsToFtp.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${RotateLogsToFtp.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.akka.stream.alpakka.base_url" -> s"https://doc.akka.io/api/alpakka/${Dependencies.RotateLogsToFtp.PekkoConnectorsVersion}",
  "javadoc.akka.base_url" -> "",
  "extref.alpakka.base_url" -> s"https://doc.akka.io/docs/alpakka/${Dependencies.RotateLogsToFtp.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.akka.base_url" -> s"https://doc.akka.io/api/akka/${Dependencies.RotateLogsToFtp.PekkoVersion}",
  "javadoc.akka.base_url" -> s"https://doc.akka.io/japi/akka/${Dependencies.RotateLogsToFtp.PekkoVersion}",
  "extref.akka.base_url" -> s"https://doc.akka.io/docs/akka/${Dependencies.RotateLogsToFtp.PekkoVersion}/%s",
)
RotateLogsToFtp / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))


Paradox / siteSubdirName := ""
paradoxProperties ++= Map(
  "extref.akka.base_url" -> "https://doc.akka.io/docs/akka/current/",
  "extref.alpakka.base_url" -> "https://doc.akka.io/docs/alpakka/current/",
  "extref.pekko-connectors-kafka.base_url" -> "https://doc.akka.io/docs/pekko-connectors-kafka/current/",
  "extref.ftp-to-file.base_url" -> s"${(FtpToFile / siteSubdirName).value}/",
  "extref.http-csv-to-kafka.base_url" -> s"${(HttpCsvToKafka / siteSubdirName).value}/",
  "extref.jdbc-to-elasticsearch.base_url" -> s"${(JdbcToElasticsearch / siteSubdirName).value}/",
  "extref.jms.base_url" -> s"${(Jms / siteSubdirName).value}/",
  "extref.kafka-to-elasticsearch.base_url" -> s"${(KafkaToElasticsearch / siteSubdirName).value}/",
  "extref.kafka-to-websocket-clients.base_url" -> s"${(KafkaToWebsocketClients / siteSubdirName).value}/",
  "extref.mqtt-to-kafka.base_url" -> s"${(MqttToKafka / siteSubdirName).value}/",
  "extref.file-to-elasticsearch.base_url" -> s"${(FileToElasticsearch / siteSubdirName).value}/",
  "extref.rotate-logs-to-ftp.base_url" -> s"${(RotateLogsToFtp / siteSubdirName).value}/",
  "extref.pekko.base_url" -> "https://pekko.apache.org/docs/pekko/current/",
  "extref.pekko-connectors.base_url" -> "https://pekko.apache.org/docs/pekko-connectors/current/",
)
