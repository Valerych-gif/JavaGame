package ru.bulekov.game.scene;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.GameObject;

import java.util.ArrayList;
import java.util.List;

public class MenuScene extends Scene{

    private final List<GameObject> gameObjects;

    public MenuScene(String name) {
        super(name);
        gameObjects = new ArrayList<>();
    }

    public void init(Game game){
        super.init(game);
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
