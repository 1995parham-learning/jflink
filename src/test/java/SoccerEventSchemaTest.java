import home.parham.jflink.domain.SoccerEvent;
import home.parham.jflink.marshal.SoccerEventSchema;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoccerEventSchemaTest {
    @Test
    public void deserializeFromString() {
        SoccerEventSchema schema = new SoccerEventSchema();

        SoccerEvent event = schema.deserialize(
                new StringBuilder()
                        .append("{\n")
                        .append("\t\"timestamp\": \"2016-11-01T20:44:39+03:30\",\n")
                        .append("\t\"type\": \"Foul\",\n")
                        .append("\t\"team\": 0\n")
                        .append("}")
                        .toString()
        );

        assertEquals(event.timestamp, Timestamp.valueOf("2016-11-01 20:44:39"));
    }
}
