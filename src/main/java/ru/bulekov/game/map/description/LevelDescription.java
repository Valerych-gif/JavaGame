package ru.bulekov.game.map.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LevelDescription {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("max_width")
    private int maxWidth;
    @JsonProperty("max_height")
    private int maxHeight;
    @JsonProperty("assets")
    private AssetsDescription assets;
}
