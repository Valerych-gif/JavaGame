package ru.bulekov.game.core;

import lombok.Data;
import ru.bulekov.game.asset.AssetsHandler;
import ru.bulekov.game.config.GameSettingsLoader;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CollisionsHandler;
import ru.bulekov.game.render.DebugRenderer;
import ru.bulekov.game.render.MainRenderer;
import ru.bulekov.game.scene.GameScene;
import ru.bulekov.game.scene.MenuScene;
import ru.bulekov.game.scene.Scene;

@Data
public class Game {
    private Scene scene;
    private MainRenderer renderer;
    private DebugRenderer debugRenderer;
    private GameKeyListener keyListener;
    private Scene gameScene, menuScene;
    private CollisionsHandler collisionsHandler;
    private GameSettingsLoader settingsLoader;
    private Settings settings;
    private AssetsHandler assetsHandler;


    private int width;
    private int height;

    public Game() {
        initVariables();
        initGame();
    }

    private void initVariables() {
        settingsLoader = new GameSettingsLoader();
        settings = settingsLoader.loadSettings();
        assetsHandler = new AssetsHandler();
        width = (int) settings.getValue("WINDOW_WIDTH");
        height = (int) settings.getValue("WINDOW_HEIGHT");
        keyListener = new GameKeyListener();
        renderer = new MainRenderer();
        debugRenderer = new DebugRenderer();
        collisionsHandler = new CollisionsHandler();
        gameScene = new GameScene("GameScene");
        menuScene = new MenuScene("MenuScene");
        scene = gameScene;
    }

    private void initGame() {
        renderer.setScene(scene);
        assetsHandler.init(this);
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
}
