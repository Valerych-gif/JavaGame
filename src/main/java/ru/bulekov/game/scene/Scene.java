package ru.bulekov.game.scene;

import lombok.Data;
import ru.bulekov.game.asset.AssetsHandler;
import ru.bulekov.game.core.Game;
import ru.bulekov.game.render.MainRenderer;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.render.DebugRenderer;

import java.awt.*;
import java.util.List;

@Data
public abstract class Scene {

    public static final int MENU = 0;
    public static final int GAME = 1;
    protected String name;
    protected Game game;
    protected MainRenderer renderer;
    protected DebugRenderer debugRenderer;
    protected Canvas canvas;
    protected GameKeyListener keyListener;
    protected AssetsHandler assetsHandler;

    public Scene() {
    }

    public Scene(String name) {
        this.name = name;
    }

    public void init(Game game) {
        this.game = game;
        this.renderer = game.getRenderer();
        this.debugRenderer = game.getDebugRenderer();
        this.canvas = renderer.getCanvas();
        this.keyListener = game.getKeyListener();
        this.assetsHandler = game.getAssetsHandler();
    }

    public abstract void update(float dt);

    public void render(){
        debugRenderer.render();
        renderer.render();
    }

    public abstract List<GameObject> getGameObjects();
}
