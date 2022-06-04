package ru.bulekov.game.map.description;

import lombok.Data;
import ru.bulekov.game.map.description.LevelDescription;

import java.util.List;

@Data
public class MapDescription {
    private LevelDescription description;
    private List<Integer> terrain;
    private List<Integer> gameObjects;
    private List<Integer> character;
}
