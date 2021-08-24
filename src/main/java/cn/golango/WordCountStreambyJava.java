package cn.golango;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


import java.util.Arrays;

public class WordCountStreambyJava {

    public static void main(String[] args) throws Exception {


        //启动服务 nc -lp 18880
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        DataStreamSource<String> source = environment.socketTextStream("localhost", 18880);

        SingleOutputStreamOperator<String> flatMap = source.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String line, Collector<String> collector) throws Exception {
                Arrays.stream(line.split(" ")).forEach(x -> collector.collect(x));
            }
        });

        SingleOutputStreamOperator<Tuple2<String, Integer>> map = flatMap.map(new MapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public Tuple2<String, Integer> map(String s) throws Exception {
                return Tuple2.of(s, 1);
            }
        });

        map.keyBy(0).sum(1).print();

        // 开启流式处理
        environment.execute("WordCountStreambyJava" + System.currentTimeMillis());

    }

}
