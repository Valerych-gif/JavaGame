package ru.bulekov.game.scene;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.Enemy;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.player.Player;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CollisionsHandler;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {

    private List<GameObject> gameObjects;
    private GameKeyListener keyListener;
    private CollisionsHandler collisionsHandler;

    public GameScene(String name) {
        super(name);
    }

    public void init(Game game){
        super.init(game);
        this.keyListener = game.getKeyListener();
        this.collisionsHandler = game.getCollisionsHandler();
        gameObjects = new ArrayList<>();

        Enemy enemy = new Enemy("Enemy", this);
        enemy.setPosition(new Position(new Vector2(100, 100)));
        gameObjects.add(enemy);

        Enemy enemy1 = new Enemy("Enemy1", this);
        enemy1.setPosition(new Position(new Vector2(200, 100)));
        gameObjects.add(enemy1);

        Player player = new Player(this);
        player.setPosition(new Position(new Vector2(300, 100)));
        gameObjects.add(player);

        Player player2 = new Player(this);
        player2.setPosition(new Position(new Vector2(400, 100)));
        gameObjects.add(player2);

    }

    @Override
    public void update(float dt) {
        if (keyListener.getKeyPressed()[KeyEvent.VK_ESCAPE]){
            game.setScene(Scene.MENU);
        }
        getGameObjects().forEach(gameObject -> gameObject.update(dt));
//        collisionsHandler.checkCollides();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public CollisionsHandler getCollisionsHandler() {
        return collisionsHandler;
    }
}

