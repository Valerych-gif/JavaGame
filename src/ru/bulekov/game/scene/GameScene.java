package ru.bulekov.game.scene;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.Enemy;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.Player;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.input.GameKeyListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {

    private final List<GameObject> gameObjects;
    private final Game game;
    private final GameKeyListener keyListener;

    public GameScene(String name, Game game) {
        super(name, game);
        this.game = game;
        this.keyListener = game.getKeyListener();
        gameObjects = new ArrayList<>();

        Player player = new Player(this);
        player.setPosition(new Position(0, 0));
        gameObjects.add(player);

        Enemy enemy = new Enemy(this);
        enemy.setPosition(new Position(100, 100));
        gameObjects.add(enemy);
    }

    @Override
    public void update(float dt) {
        if (keyListener.getKeyPressed()[KeyEvent.VK_ESCAPE]){
            game.setScene(Scene.MENU);
        }
        getGameObjects().forEach(gameObject -> gameObject.update(dt));
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

