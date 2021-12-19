package ru.bulekov.game.scene;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.render.MainRenderer;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.render.DebugRenderer;

import java.awt.*;
import java.util.List;

public abstract class Scene {

    public static final int MENU = 0;
    public static final int GAME = 1;
    protected String name;
    protected MainRenderer renderer;
    protected DebugRenderer debugRenderer;
    protected Canvas canvas;

    public Scene(String name, Game game) {
        this.name = name;
        this.renderer = game.getRenderer();
        this.debugRenderer = game.getDebugRenderer();
        getGraphics();
    }

    private void getGraphics() {
        canvas = renderer.getCanvas();
    }

    public abstract void update(float dt);

    public void render(){
        debugRenderer.render();
        renderer.render();
    }

    public abstract List<GameObject> getGameObjects();

    public GameKeyListener getKeyListener() {
        return renderer.getKeyListener();
    }
}
