package ru.bulekov.game.physic;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;

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
            if (collider instanceof CircleCollider) {
                CircleCollider thisCollider = (CircleCollider) collider;
                gameObjects.forEach(otherGameObject -> {
                    if (!gameObject.equals(otherGameObject)) {
                        Map<String, Collider> otherColliders = otherGameObject.getColliders();
                        otherColliders.forEach((otherName, otherCollider) -> {
                            if ((thisCollider.getPosition().distance(otherCollider.getPosition())) < thisCollider.getRadius()) {
                                Vector2 direction = new Vector2(
                                        thisCollider.getPosition().getX() - otherCollider.getPosition().getX(),
                                        thisCollider.getPosition().getY() - otherCollider.getPosition().getY()
                                        ).normalize();

                                Force force = gameObject.getForce();
                                float contraForceX = direction.getX()*force.getForceVector2().getX() * 2;
                                Force contraForce = new Force(
                                        "ContraForce",
                                        new Vector2(contraForceX, direction.getY()*5)
                                );
                                force.add(contraForce);
                                Force otherForce = otherGameObject.getForce();
                                otherForce.add(force);
                            }
                        });
                    }
                });

            } else if (collider instanceof BoxCollider) {
                BoxCollider thisCollider = (BoxCollider) collider;
                System.out.println(thisCollider.getName());
            }

        });
    }

    public void init(Game game) {
        this.game = game;
    }
}
