package cn.golango;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

public class Hello02FlinkOperatorMap {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> source = environment.readTextFile("data/dataset.txt");

//        source.map(new MapFunction<String, String>() {
//            @Override
//            public String map(String s) throws Exception {
//                return s.toUpperCase();
//            }
//        }).print();


        source.map(x -> x.toUpperCase()).print();
    }
}
