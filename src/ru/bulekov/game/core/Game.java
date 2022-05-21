package ru.bulekov.game.core;

import ru.bulekov.game.config.GameSettingsLoader;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CollisionsHandler;
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
    private final CollisionsHandler collisionsHandler;
    private final Settings settings;


    private final int width;
    private final int height;

    public Game() {
        this.settings = GameSettingsLoader.loadSettings();
        this.width = (int) settings.getValue("WINDOW_WIDTH");
        this.height = (int) settings.getValue("WINDOW_HEIGHT");
        this.keyListener = new GameKeyListener();
        this.renderer = new MainRenderer();
        this.debugRenderer = new DebugRenderer();
        this.collisionsHandler = new CollisionsHandler();
        this.gameScene = new GameScene("GameScene");
        this.menuScene = new MenuScene("MenuScene");
        this.scene = gameScene;
        renderer.setScene(scene);
        init();
    }

    private void init() {
        keyListener.init();
        gameScene.init(this);
        menuScene.init(this);
        renderer.init(this);
        debugRenderer.init(this);
        collisionsHandler.init(this);
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
                throw new IllegalStateException("Illegal state number: " + sceneNum);
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

    public CollisionsHandler getCollisionsHandler() {
        return collisionsHandler;
    }

    public Settings getSettings() {
        return settings;
    }
}
