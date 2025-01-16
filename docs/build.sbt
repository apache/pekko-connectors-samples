
ThisBuild / scalaVersion := "2.13.16"

enablePlugins(ParadoxPlugin, PekkoParadoxPlugin, ParadoxSitePlugin)

Global / pekkoParadoxIncubatorNotice := None

name := "Pekko Connectors Samples"
previewFixedPort := Some(8085)
scmInfo := Some(ScmInfo(url("https://github.com/apache/pekko-connectors-samples"), "git@github.com:apache/pekko-connectors-samples.git"))
homepage := Some(url("https://github.com/apache/pekko-connectors-samples"))
description := "Example solutions for Enterprise Integrations using Pekko Connectors and Reactive Streams."
version := {
  val time = java.time.LocalDateTime.now().withSecond(0).withNano(0)
  java.time.format.DateTimeFormatter.ISO_DATE.format(time) + " " + java.time.format.DateTimeFormatter.ISO_TIME.format(time)
}
isSnapshot := true

pekkoParadoxGithub := Some("https://github.com/apache/pekko-connectors-samples")

val FtpToFile = config("ftp-to-file")
ParadoxPlugin.paradoxSettings(FtpToFile)
ParadoxSitePlugin.paradoxSettings(FtpToFile)
PekkoParadoxPlugin.pekkoParadoxSettings(FtpToFile)
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
PekkoParadoxPlugin.pekkoParadoxSettings(HttpCsvToKafka)
HttpCsvToKafka / siteSubdirName := HttpCsvToKafka.name
HttpCsvToKafka / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${HttpCsvToKafka.name}" / "docs" / "src" / "main" / "paradox"
HttpCsvToKafka / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${HttpCsvToKafka.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${HttpCsvToKafka.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${HttpCsvToKafka.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.HttpCsvToKafka.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.HttpCsvToKafka.PekkoConnectorsVersion}/%s",
  // Pekko Connectors Kafka
  "scaladoc.org.apache.pekko.kafka.base_url" -> s"https://pekko.apache.org/api/pekko-connectors-kafka/${Dependencies.HttpCsvToKafka.PekkoConnectorsKafkaVersion}",
  "javadoc.org.apache.pekko.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors-kafka/${Dependencies.HttpCsvToKafka.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.HttpCsvToKafka.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.HttpCsvToKafka.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.HttpCsvToKafka.PekkoVersion}/%s",
  // Pekko HTTP
  "scaladoc.org.apache.pekko.http.base_url" -> s"https://pekko.apache.org/api/pekko-http/${Dependencies.HttpCsvToKafka.PekkoHttpVersion}",
  "javadoc.org.apache.pekko.http.base_url" -> s"https://pekko.apache.org/japi/pekko-http/${Dependencies.HttpCsvToKafka.PekkoHttpVersion}",
  "extref.pekko-http.base_url" -> s"https://pekko.apache.org/docs/pekko-http/${Dependencies.HttpCsvToKafka.PekkoHttpVersion}/%s",
)
HttpCsvToKafka / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val JdbcToElasticsearch = config("jdbc-to-elasticsearch")
ParadoxPlugin.paradoxSettings(JdbcToElasticsearch)
ParadoxSitePlugin.paradoxSettings(JdbcToElasticsearch)
PekkoParadoxPlugin.pekkoParadoxSettings(JdbcToElasticsearch)
JdbcToElasticsearch / siteSubdirName := JdbcToElasticsearch.name
JdbcToElasticsearch / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${JdbcToElasticsearch.name}" / "docs" / "src" / "main" / "paradox"
JdbcToElasticsearch / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${JdbcToElasticsearch.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${JdbcToElasticsearch.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${JdbcToElasticsearch.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.JdbcToElasticsearch.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.JdbcToElasticsearch.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.JdbcToElasticsearch.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.JdbcToElasticsearch.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.JdbcToElasticsearch.PekkoVersion}/%s",
)
JdbcToElasticsearch / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val Jms = config("jms")
ParadoxPlugin.paradoxSettings(Jms)
ParadoxSitePlugin.paradoxSettings(Jms)
PekkoParadoxPlugin.pekkoParadoxSettings(Jms)
Jms / siteSubdirName := Jms.name
Jms / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${Jms.name}" / "docs" / "src" / "main" / "paradox"
Jms / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${Jms.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${Jms.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${Jms.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.Jms.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.Jms.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.Jms.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.Jms.PekkoVersion}",
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
PekkoParadoxPlugin.pekkoParadoxSettings(KafkaToElasticsearch)
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
PekkoParadoxPlugin.pekkoParadoxSettings(KafkaToWebsocketClients)
KafkaToWebsocketClients / siteSubdirName := KafkaToWebsocketClients.name
KafkaToWebsocketClients / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${KafkaToWebsocketClients.name}" / "docs" / "src" / "main" / "paradox"
KafkaToWebsocketClients / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${KafkaToWebsocketClients.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${KafkaToWebsocketClients.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${KafkaToWebsocketClients.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors Kafka
  "javadoc.org.apache.pekko.kafka.base_url" -> s"https://pekko.apache.org/api/pekko-connectors-kafka/${Dependencies.KafkaToWebsocketClients.PekkoConnectorsKafkaVersion}",
  "javadoc.org.apache.pekko.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors-kafka/${Dependencies.KafkaToWebsocketClients.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.KafkaToWebsocketClients.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.KafkaToWebsocketClients.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.KafkaToWebsocketClients.PekkoVersion}/%s",
  // Pekko HTTP
  "scaladoc.org.apache.pekko.http.base_url" -> s"https://pekko.apache.org/api/pekko-http/${Dependencies.KafkaToWebsocketClients.PekkoHttpVersion}",
  "javadoc.org.apache.pekko.http.base_url" -> s"https://pekko.apache.org/japi/pekko-http/${Dependencies.KafkaToWebsocketClients.PekkoHttpVersion}",
  "extref.pekko-http.base_url" -> s"https://pekko.apache.org/docs/pekko-http/${Dependencies.KafkaToWebsocketClients.PekkoHttpVersion}/%s",
)
KafkaToWebsocketClients / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))

val MqttToKafka = config("mqtt-to-kafka")
ParadoxPlugin.paradoxSettings(MqttToKafka)
ParadoxSitePlugin.paradoxSettings(MqttToKafka)
PekkoParadoxPlugin.pekkoParadoxSettings(MqttToKafka)
MqttToKafka / siteSubdirName := MqttToKafka.name
MqttToKafka / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${MqttToKafka.name}" / "docs" / "src" / "main" / "paradox"
MqttToKafka / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${MqttToKafka.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${MqttToKafka.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${MqttToKafka.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.MqttToKafka.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.MqttToKafka.PekkoConnectorsVersion}/%s",
  // Pekko Connectors Kafka
  "javadoc.org.apache.pekko.kafka.base_url" -> s"https://pekko.apache.org/api/pekko-connectors-kafka/${Dependencies.MqttToKafka.PekkoConnectorsKafkaVersion}",
  "javadoc.org.apache.pekko.kafka.base_url" -> "",
  "extref.pekko-connectors-kafka.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors-kafka/${Dependencies.MqttToKafka.PekkoConnectorsKafkaVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.MqttToKafka.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.MqttToKafka.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.MqttToKafka.PekkoVersion}/%s",
)
MqttToKafka / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))


val FileToElasticsearch = config("file-to-elasticsearch")
ParadoxPlugin.paradoxSettings(FileToElasticsearch)
ParadoxSitePlugin.paradoxSettings(FileToElasticsearch)
PekkoParadoxPlugin.pekkoParadoxSettings(FileToElasticsearch)
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
PekkoParadoxPlugin.pekkoParadoxSettings(RotateLogsToFtp)
RotateLogsToFtp / siteSubdirName := RotateLogsToFtp.name
RotateLogsToFtp / paradox / sourceDirectory := baseDirectory.value / ".." / s"pekko-connectors-sample-${RotateLogsToFtp.name}" / "docs" / "src" / "main" / "paradox"
RotateLogsToFtp / paradoxProperties ++= Map(
  "project.url" -> s"${homepage.value.get}/${RotateLogsToFtp.name}",
  "canonical.base_url" -> s"${homepage.value.get}/${RotateLogsToFtp.name}",
  "snip.build.base_dir" -> s"${baseDirectory.value}/../pekko-connectors-sample-${RotateLogsToFtp.name}",
  "github.root.base_dir" -> s"${baseDirectory.value}/..",
  // Pekko Connectors
  "scaladoc.org.apache.pekko.stream.connectors.base_url" -> s"https://pekko.apache.org/api/pekko-connectors/${Dependencies.RotateLogsToFtp.PekkoConnectorsVersion}",
  "javadoc.org.apache.pekko.stream.connectors.base_url" -> "",
  "extref.pekko-connectors.base_url" -> s"https://pekko.apache.org/docs/pekko-connectors/${Dependencies.RotateLogsToFtp.PekkoConnectorsVersion}/%s",
  // Pekko
  "scaladoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/api/pekko/${Dependencies.RotateLogsToFtp.PekkoVersion}",
  "javadoc.org.apache.pekko.base_url" -> s"https://pekko.apache.org/japi/pekko/${Dependencies.RotateLogsToFtp.PekkoVersion}",
  "extref.pekko.base_url" -> s"https://pekko.apache.org/docs/pekko/${Dependencies.RotateLogsToFtp.PekkoVersion}/%s",
)
RotateLogsToFtp / paradoxGroups := Map("Language" -> Seq("Java", "Scala"))


Paradox / siteSubdirName := ""
paradoxProperties ++= Map(
  "extref.pekko.base_url" -> "https://pekko.apache.org/docs/pekko/current/",
  "extref.pekko-connectors.base_url" -> "https://pekko.apache.org/docs/pekko-connectors/current/",
  "extref.pekko-connectors-kafka.base_url" -> "https://pekko.apache.org/docs/pekko-connectors-kafka/current/",
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
  // disabled display versioning, in other words: hidden unnecessary version.
  "disabled.versioning.display" -> "true"
)
