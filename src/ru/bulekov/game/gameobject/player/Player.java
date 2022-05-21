package ru.bulekov.game.gameobject.player;

import ru.bulekov.game.asset.GameObjectDescriptionHandler;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.state.*;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.CircleCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

import static ru.bulekov.game.config.GameConstants.debugMode;

public class Player extends GameObject {

    private PlayerDescription description;
    private final GameKeyListener keyListener;

    public Player(Scene scene) {

        super("Player", scene);
        this.position = new Position();
        this.keyListener = scene.getKeyListener();

        getPlayerDescription();
        setAnimations();
        setStates();
        setColliders();
    }

    private void setColliders() {
        Collider mainCollider = new CircleCollider("PlayerMainCollider", position, (int) Settings.getValue("player_sprite_size"), this);
        colliders.put(mainCollider.getName(), mainCollider);
    }

    private void setStates() {
        this.fallingLeftState = new FallingLeftState(this);
        this.fallingRightState = new FallingRightState(this);
        this.jumpingLeftState = new JumpingLeftState(this);
        this.jumpingRightState = new JumpingRightState(this);
        this.standingLeftState = new StandingLeftState(this);
        this.standingRightState = new StandingRightState(this);
        this.movingLeftState = new MovingLeftState(this);
        this.movingRightState = new MovingRightState(this);

        this.state = new FallingRightState(this);
    }

    private void setAnimations() {
        this.movingRightAnimation = description.getAnimation("standing_right"); //new Animation("standing_right.png", ANIMATION_FILE, 1, 1);
        this.movingLeftAnimation = description.getAnimation("standing_left"); //new Animation("standing_left.png", ANIMATION_FILE, 1, 10);

        this.animation = movingRightAnimation;
    }

    private void getPlayerDescription() {
        this.description = (PlayerDescription) GameObjectDescriptionHandler.get("Player", new PlayerDescription());
        this.weight = description.getWeight();
        this.maxSpeed = description.getMaxSpeed();
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
