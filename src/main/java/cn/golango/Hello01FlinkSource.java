package cn.golango;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

public class Hello01FlinkSource {

    public static void main(String[] args) {

        // 创建环境
        ExecutionEnvironment environmentBatch = ExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        StreamExecutionEnvironment environmentStream = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        // 获取数据源（文件）
        DataSource<String> fileSourceBatch = environmentBatch.readTextFile("path");
        DataStreamSource<String> fileSourceStream = environmentStream.readTextFile("path");

        // 获取数据源（容器）
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        DataSource<String> collectionBatch = environmentBatch.fromCollection(list);
        DataStreamSource<String> collectionStream = environmentStream.fromCollection(list);

        // 获取数据源（流处理）
        environmentStream.socketTextStream("localhost", 18880);

    }

}
