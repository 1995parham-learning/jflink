import home.parham.jflink.domain.SoccerEvent;
import home.parham.jflink.domain.SoccerEventType;
import home.parham.jflink.op.SoccerEventTypeCounter;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.operators.StreamFlatMap;
import org.apache.flink.streaming.runtime.streamrecord.StreamRecord;
import org.apache.flink.streaming.util.OneInputStreamOperatorTestHarness;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

public class SoccerEventTypeCounterTest {
    private OneInputStreamOperatorTestHarness<SoccerEvent, Tuple2<String, Integer>> testHarness;

    @BeforeEach
    public void setupTestHarness() throws Exception {

        SoccerEventTypeCounter soccerEventTypeCounter = new SoccerEventTypeCounter();

        testHarness = new OneInputStreamOperatorTestHarness<>(new StreamFlatMap<>(soccerEventTypeCounter));

        // open the test harness (will also call open() on RichFunctions)
        testHarness.open();
    }

    @Test
    public void testingStatefulFlatMapFunction() throws Exception {
        SoccerEvent event = new SoccerEvent();
        event.type = SoccerEventType.Foul;
        event.team = 0;
        event.timestamp = Timestamp.valueOf("2019-12-01 18:00:00");

        // push (timestamped) elements into the operator (and hence user defined function)
        testHarness.processElement(event, 100L);
        testHarness.processElement(event, 101L);

        // retrieve list of emitted records for assertions
        List<StreamRecord<? extends Tuple2<String, Integer>>> ll = testHarness.extractOutputStreamRecords();

        assertEquals(ll.get(0).getValue(), new Tuple2<>(SoccerEventType.Foul.name(), 1));
        assertEquals(ll.get(1).getValue(), new Tuple2<>(SoccerEventType.Foul.name(), 1));
    }
}
