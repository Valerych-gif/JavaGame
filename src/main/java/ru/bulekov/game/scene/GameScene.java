package ru.bulekov.game.scene;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.player.Player;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CollisionsHandler;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class GameScene extends Scene {

    private List<GameObject> gameObjects;
    private CollisionsHandler collisionsHandler;

    public GameScene(String name) {
        super(name);
    }

    public void init(Game game){
        super.init(game);
        this.collisionsHandler = game.getCollisionsHandler();

        gameObjects = new ArrayList<>();

        Player player = new Player(this);
        player.setPosition(new Position(new Vector2(300, 500)));
        gameObjects.add(player);

    }

    @Override
    public void update(float dt) {
        if (keyListener.getKeyPressed()[KeyEvent.VK_ESCAPE]){
            game.setScene(Scene.MENU);
        }
        getGameObjects().forEach(gameObject -> gameObject.update(dt));
    }

    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public CollisionsHandler getCollisionsHandler() {
        return collisionsHandler;
    }
}

