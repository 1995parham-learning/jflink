package home.parham.jflink.marshal;

import com.google.gson.Gson;
import home.parham.jflink.domain.SoccerEvent;

public class SoccerEventSchema {
    public SoccerEvent deserialize(String message) {
        System.out.println(message);
        Gson gson = new Gson();
        SoccerEvent event = gson.fromJson(message, SoccerEvent.class);
        System.out.println(event);
        return event;
    }
}
