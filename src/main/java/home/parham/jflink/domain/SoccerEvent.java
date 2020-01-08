package home.parham.jflink.domain;

import java.sql.Timestamp;

public class SoccerEvent {
    private Timestamp timestamp;
    private SoccerEventType type;
    private int team;

    public SoccerEvent() {

    }

    public SoccerEvent(Timestamp timestamp, SoccerEventType type, int team) {
        this.timestamp = timestamp;
        this.type = type;
        this.team = team;
    }

    public int getTeam() {
        return team;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public SoccerEventType getType() {
        return type;
    }
}
