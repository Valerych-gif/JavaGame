package ru.bulekov.game.gameobject.player;

import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.asset.GameObjectDescriptionHandler;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.ObjectDescription;
import ru.bulekov.game.gameobject.state.*;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.physic.CircleCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.render.AnimationSettings;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

import static ru.bulekov.game.config.GameConstants.debugMode;

@Slf4j
public class Player extends GameObject {

    private ObjectDescription description;

    public Player(Scene scene) {

        super("Player", scene);
        this.position = new Position();
        this.keyListener = scene.getKeyListener();
        this.assetsHandler = scene.getGame().getAssetsHandler();
        this.standingRightAnimationSetting = AnimationSettings.builder()
                .animationName("standing_right.png")
                .fileName("images/tank.json")
                .framesNumber(1)
                .framesPerSecond(2)
                .build();

        getPlayerDescription();
        setStates();
        setColliders();

        log.info("Created Player object {}", description);
    }

    private void setColliders() {
        Collider mainCollider = new CircleCollider("PlayerMainCollider", position, (int) description.getWidth(), this);
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

    private void getPlayerDescription() {
        GameObjectDescriptionHandler descriptionHandler = new GameObjectDescriptionHandler();
        this.description = descriptionHandler.get(assetsHandler, "Player");
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
}
