package demo

import org.apache.spark.sql.SparkSession


object SparkDemo {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder
      .master("local[*]")
      .appName("JSON Reader")
      .getOrCreate()
    val json =
      """
        |{
        | "id":1,
        | "name":"我、我"
        |}
        |""".stripMargin
    import spark.implicits._
    val jsonDF = spark.read.json(Seq(json).toDS)
    // 显示数据框架中的内容
    jsonDF.show()
  }

}
