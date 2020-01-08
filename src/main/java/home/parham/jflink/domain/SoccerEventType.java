package home.parham.jflink.domain;

import com.google.gson.annotations.SerializedName;

public enum SoccerEventType {
    @SerializedName("Goal")
    Goal,
    @SerializedName("Penalty")
    Penalty,
    @SerializedName("Offside")
    Offside,
    @SerializedName("Corner")
    Corner,
    @SerializedName("FreeKick")
    FreeKick,
    @SerializedName("Assist")
    Assist,
    @SerializedName("Foul")
    Foul,
}
