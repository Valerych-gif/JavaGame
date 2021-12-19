package ru.bulekov.game.scene;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

public class MenuScene extends Scene{

    private final List<GameObject> gameObjects;

    public MenuScene(String name, Game game) {
        super(name, game);
        gameObjects = new ArrayList<>();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
