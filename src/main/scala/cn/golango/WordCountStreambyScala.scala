package cn.golango

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._;

object WordCountStreambyScala {

  def main(args: Array[String]): Unit = {

    val environment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration())
    val source: DataStream[String] = environment.socketTextStream("localhost", 18880)

    source.flatMap(_.split(" ")).map((_, 1)).keyBy(0).sum(1).print()

    environment.execute("WordCountStreambyScala" + System.currentTimeMillis())
  }

}
