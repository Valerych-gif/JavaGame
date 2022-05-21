package ru.bulekov.game.gameobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ObjectDescription{
    @JsonProperty("object_name")
    private String objectName;
    @JsonProperty("width")
    private float width;
    @JsonProperty("weight")
    private float weight;
    @JsonProperty("max_speed")
    private float maxSpeed;
    @JsonProperty("states_list")
    private List<String> states;
}
