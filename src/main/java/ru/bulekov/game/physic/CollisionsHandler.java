package ru.bulekov.game.physic;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.physic.collider.Collider;

import java.util.List;
import java.util.Map;

public class CollisionsHandler {

    private Game game;

    public CollisionsHandler() {

    }

    public void checkCollides(GameObject gameObject) {
        List<GameObject> gameObjects = game.getScene().getGameObjects();
        Map<String, Collider> colliders = gameObject.getColliders();
        colliders.forEach((name, collider) -> {
            gameObjects.forEach(otherGameObject -> {
                if (!gameObject.equals(otherGameObject)){
                    otherGameObject.getColliders().forEach((otherColliderName, otherCollider) -> {
                        collider.checkCollide(otherCollider);
                    });
                }
            });

        });
    }

    public void init(Game game) {
        this.game = game;
    }
}
