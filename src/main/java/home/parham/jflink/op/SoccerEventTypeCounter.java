package home.parham.jflink.op;

import home.parham.jflink.domain.SoccerEvent;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class SoccerEventTypeCounter implements FlatMapFunction<SoccerEvent, Tuple2<String, Integer>> {
    @Override
    public void flatMap(SoccerEvent value, Collector<Tuple2<String, Integer>> out) throws Exception {
        System.out.println(value.team);
        System.out.println(value.timestamp);
        System.out.println(value.type);
        out.collect(new Tuple2<>(value.type.name(), 1));
    }
}
