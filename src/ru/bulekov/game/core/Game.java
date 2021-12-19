package ru.bulekov.game.core;

import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.render.DebugRenderer;
import ru.bulekov.game.render.MainRenderer;
import ru.bulekov.game.scene.GameScene;
import ru.bulekov.game.scene.MenuScene;
import ru.bulekov.game.scene.Scene;

public class Game {
    private Scene scene;
    private final MainRenderer renderer;
    private final DebugRenderer debugRenderer;
    private final GameKeyListener keyListener;

    private final Scene gameScene, menuScene;

    private final int width;
    private final int height;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        keyListener = new GameKeyListener();
        this.renderer = new MainRenderer(this);
        this.debugRenderer = new DebugRenderer(this);
        gameScene = new GameScene("GameScene", this);
        menuScene = new MenuScene("MenuScene", this);
        this.scene = gameScene;
        renderer.setScene(scene);
    }


    public void update(float dt){
        scene.update(dt);
    }

    public void render(){
        scene.render();
    }

    public void setScene(int sceneNum){
        switch (sceneNum){
            case Scene.MENU:
                this.scene = menuScene;
                break;
            case Scene.GAME:
                this.scene = gameScene;
                break;
            default:
                this.scene = menuScene;
        }
        renderer.setScene(scene);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Scene getScene() {
        return scene;
    }

    public MainRenderer getRenderer() {
        return renderer;
    }

    public DebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public GameKeyListener getKeyListener() {
        return keyListener;
    }
}
