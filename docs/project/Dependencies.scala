import sbt._
import scala.meta._

object Dependencies {

  object FtpToFile {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-ftp-to-file" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
  }

  object HttpCsvToKafka {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-http-csv-to-kafka" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val ScalaTestVersion = versions("ScalaTestVersion")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoHttpVersion = versions("PekkoHttpVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
    val PekkoConnectorsKafkaVersion = versions("PekkoConnectorsKafkaVersion")
  }

  object JdbcToElasticsearch {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-jdbc-to-elasticsearch" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
  }

  object Jms {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-jms" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoHttpVersion = versions("PekkoHttpVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
  }

  object KafkaToElasticsearch {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-kafka-to-elasticsearch" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
    val PekkoConnectorsKafkaVersion = versions("PekkoConnectorsKafkaVersion")
  }
  
  object KafkaToWebsocketClients {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-kafka-to-websocket-clients" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoHttpVersion = versions("PekkoHttpVersion")
    val PekkoConnectorsKafkaVersion = versions("PekkoConnectorsKafkaVersion")
  }

  object MqttToKafka {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-mqtt-to-kafka" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
    val PekkoConnectorsKafkaVersion = versions("PekkoConnectorsKafkaVersion")
  }

  object FileToElasticsearch {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-file-to-elasticsearch" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
  }

  object RotateLogsToFtp {
    val versions = {
      val source = IO.read(file(".") / ".." / "pekko-connectors-sample-rotate-logs-to-ftp" / "project" / "Dependencies.scala")
      val tree = source.parse[Source].get
      tree.collect {
        case q"val ${v: Pat.Var} = ${s: Lit.String}" => v.name.value -> s.value
      }.toMap
    }

    val ScalaVersion = versions("scalaVer")
    val PekkoVersion = versions("PekkoVersion")
    val PekkoConnectorsVersion = versions("PekkoConnectorsVersion")
  }

}
