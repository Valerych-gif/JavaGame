package ru.bulekov.game.gameobject.player;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.state.FallingRight;
import ru.bulekov.game.gameobject.state.StandingRight;
import ru.bulekov.game.gameobject.state.State;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CircleCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.physic.Force;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;

public class Player extends GameObject {

    private final GameKeyListener keyListener;

    public Player(Scene scene) {
        super("Player", scene);
        this.position = new Position();
        this.weight = 100.0f;
        this.maxSpeed = 0.6f;

        this.keyListener = scene.getKeyListener();

        Collider mainCollider = new CircleCollider("PlayerMainCollider", position, SPRITE_SIZE, this);
        colliders.put(mainCollider.getName(), mainCollider);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        controller.setGoingRight(false);
        controller.setGoingLeft(false);
        controller.setJump(false);
        if ((keyListener.getKeyPressed()[KeyEvent.VK_D] || keyListener.getKeyPressed()[KeyEvent.VK_RIGHT])) {
            controller.setGoingRight(true);
        } else if ((keyListener.getKeyPressed()[KeyEvent.VK_A] || keyListener.getKeyPressed()[KeyEvent.VK_LEFT])) {
            controller.setGoingLeft(true);
        }

        if (keyListener.getKeyPressed()[KeyEvent.VK_SPACE]) {
            controller.setJump(true);
        }

        state.update(this, dt);
        colliders.get("PlayerMainCollider").setPosition(position);
    }

    @Override
    public void render(Graphics g) {
        state.render(this, g);
    }

    @Override
    public void debugRender(Graphics g) {
        colliders.forEach((name, collider) -> collider.render(g));
    }

    public Position getPosition() {
        return position;
    }

    public GameKeyListener getKeyListener() {
        return keyListener;
    }
}
