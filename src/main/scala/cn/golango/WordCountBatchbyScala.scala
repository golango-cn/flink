package cn.golango

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}

import org.apache.flink.api.scala._;

object WordCountBatchbyScala {

  def main(args: Array[String]): Unit = {

    var env = ExecutionEnvironment.getExecutionEnvironment;
    var source: DataSet[String] = env.readTextFile("data/dataset.txt");

    source.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1).print;

  }

}
