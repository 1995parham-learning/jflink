import home.parham.jflink.domain.SoccerEvent;
import home.parham.jflink.marshal.SoccerEventSchema;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoccerEventSchemaTest {
    @Test
    public void deserializeFromString() {
        SoccerEventSchema schema = new SoccerEventSchema();

        ZonedDateTime now = ZonedDateTime.now();
        String s = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        SoccerEvent event = schema.deserialize(
                new StringBuilder()
                        .append("{\n")
                        .append("\t\"timestamp\": \"").append(s).append("\",\n")
                        .append("\t\"type\": \"Foul\",\n")
                        .append("\t\"team\": 0\n")
                        .append("}")
                        .toString()
        );

        assertEquals(event.timestamp, Timestamp.valueOf(now.toLocalDateTime()));
    }
}
