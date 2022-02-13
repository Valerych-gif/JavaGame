package ru.bulekov.game.gameobject.player;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.state.*;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CircleCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.render.Animation;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;
import static ru.bulekov.game.constants.GameConstants.debugMode;

public class Player extends GameObject {

    private final String ANIMATION_FILE = "C:\\Repo\\JavaGame\\data\\assets\\images\\tank.json";

    private final GameKeyListener keyListener;

    public Player(Scene scene) {
        super("Player", scene);
        this.position = new Position();
        this.weight = 100.0f;
        this.maxSpeed = 1.5f;

        this.keyListener = scene.getKeyListener();
        this.movingRightAnimation = new Animation("moving_right.png", ANIMATION_FILE, 5, 1);
        this.movingLeftAnimation = new Animation("moving_left.png", ANIMATION_FILE, 5, 10);
        this.animation = movingRightAnimation;

        this.fallingLeftState = new FallingLeftState(this);
        this.fallingRightState = new FallingRightState(this);
        this.jumpingLeftState = new JumpingLeftState(this);
        this.jumpingRightState = new JumpingRightState(this);
        this.standingLeftState = new StandingLeftState(this);
        this.standingRightState = new StandingRightState(this);
        this.movingLeftState = new MovingLeftState(this);
        this.movingRightState = new MovingRightState(this);

        this.state = new FallingRightState(this);

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

        state.update(dt);
        colliders.get("PlayerMainCollider").setPosition(position);
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

    @Override
    public void debugRender(Graphics g) {
        if (debugMode) {
            colliders.forEach((name, collider) -> collider.render(g));
        }
    }

    public Position getPosition() {
        return position;
    }

    public GameKeyListener getKeyListener() {
        return keyListener;
    }
}
