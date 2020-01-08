package home.parham.jflink.marshal;

import com.google.gson.Gson;
import home.parham.jflink.domain.SoccerEvent;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

public class SoccerEventSchema implements DeserializationSchema<SoccerEvent>, SerializationSchema<SoccerEvent> {
    @Override
    public SoccerEvent deserialize(byte[] message) throws IOException {
        Gson gson = new Gson();
        SoccerEvent event = new SoccerEvent();
        gson.fromJson(message.toString(), SoccerEvent.class);
        return event;
    }

    @Override
    public boolean isEndOfStream(SoccerEvent nextElement) {
        return false;
    }

    @Override
    public byte[] serialize(SoccerEvent element) {
        Gson gson = new Gson();
        return gson.toJson(element).getBytes();
    }

    @Override
    public TypeInformation<SoccerEvent> getProducedType() {
        return null;
    }
}
