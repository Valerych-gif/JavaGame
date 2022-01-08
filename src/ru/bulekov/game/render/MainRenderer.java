package ru.bulekov.game.render;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.List;

import static ru.bulekov.game.constants.GameConstants.TITLE;
import static ru.bulekov.game.constants.GameConstants.debugMode;


public class MainRenderer extends JFrame {

    private Canvas canvas;
    private List<GameObject> gameObjects;
    private Game game;
    private GameKeyListener keyListener;
    private Scene scene;

    public MainRenderer(){

    }

    public void init(Game game) {
        this.game = game;
        int width = game.getWidth();
        int height = game.getHeight();
        this.scene = game.getScene();
        this.gameObjects = scene.getGameObjects();
        this.keyListener = game.getKeyListener();

        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(true);

        add(canvas);

        addKeyListener(game.getKeyListener());
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
    }

    public void render(){
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        // clear display
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gameObjects.forEach(gameObject -> gameObject.render(graphics));

        if (keyListener.getKeySwitchedOn()[KeyEvent.VK_T] || debugMode){
            gameObjects.forEach(gameObject -> gameObject.debugRender(graphics));
        }

        graphics.dispose();
        bufferStrategy.show();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        gameObjects = scene.getGameObjects();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GameKeyListener getKeyListener() {
        return game.getKeyListener();
    }
}
