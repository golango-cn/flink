package cn.golango;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

public class Hello03FlinkOperatorFilter {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<Integer> source = environment.fromElements(1, 2, 3, 4, 5, 6, 7, 8);

        source.filter(x -> x % 2 == 0).print();

    }
}
