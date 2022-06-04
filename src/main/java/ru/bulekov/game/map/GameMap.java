package ru.bulekov.game.map;

import ru.bulekov.game.asset.AssetsHandler;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.map.description.*;
import ru.bulekov.game.map.terrain.Terrain;
import ru.bulekov.game.scene.Scene;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameMap {
    private MapDescription description;
    private int levelId;
    private final AssetsHandler assetsHandler;
    private AssetsDescription assetsDescription;

    private Map<Integer, TerrainDescription> terrainDescriptions;
    private Map<Integer, GameObjectDescription> gameObjectDescriptions;
    private Map<Integer, CharacterDescription> characterDescriptions;

    private List<Terrain> terrains;
    private List<GameObject> gameObjects;
    private List<GameObject> characters;

    public GameMap(int levelId, Scene scene) {
        this.levelId = levelId;
        this.assetsHandler = scene.getAssetsHandler();
        init();
    }

    private void init() {
        this.description = assetsHandler.getMap(levelId);
        
        this.assetsDescription = description.getDescription().getAssets();
        this.terrainDescriptions = assetsDescription.getTerrain().stream().collect(Collectors.toMap(TerrainDescription::getId, Function.identity()));
        this.gameObjectDescriptions = assetsDescription.getGameObjects().stream().collect(Collectors.toMap(GameObjectDescription::getId, Function.identity()));
        this.characterDescriptions = assetsDescription.getCharacters().stream().collect(Collectors.toMap(CharacterDescription::getId, Function.identity()));

        loadAssets();
    }

    private void loadAssets() {
        this.terrains = description.getTerrain().stream()
                .map(i -> assetsHandler.getTerrain(terrainDescriptions.get(i).getName()))
                .collect(Collectors.toList());
    }

    public void update(float dt) {
        terrains.forEach(terrain -> terrain.update(dt));
    }
}
