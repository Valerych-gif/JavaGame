package ru.bulekov.game.map.description;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AssetsDescription {
    @JsonProperty("terrain")
    private List<TerrainDescription> terrain;
    @JsonProperty("game_objects")
    private List<GameObjectDescription> gameObjects;
    @JsonProperty("terrain")
    private List<CharacterDescription> characters;
}
