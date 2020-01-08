package home.parham.jflink.domain;

import java.sql.Timestamp;

enum Type {
    Goal,
    Penalty ,
    Offside,
    Corner,
    FreeKick,
    Assist,
    Foul,
}

public class SoccerEvent {
    private Timestamp timestamp;
    private Type type;
    private int team;

    public SoccerEvent() {

    }

    public SoccerEvent(Timestamp timestamp, Type type, int team) {
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

    public Type getType() {
        return type;
    }
}
