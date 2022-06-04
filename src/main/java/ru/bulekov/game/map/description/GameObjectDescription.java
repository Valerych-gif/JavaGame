package ru.bulekov.game.map.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameObjectDescription {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("path")
    private String path;
}
