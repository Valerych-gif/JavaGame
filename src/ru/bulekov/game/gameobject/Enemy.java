package ru.bulekov.game.gameobject;

import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.state.*;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.physic.CircleCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.render.Animation;
import ru.bulekov.game.scene.Scene;

import java.awt.*;

public class Enemy extends GameObject{

    public Enemy(String name, Scene scene) {
        super(name, scene);
        this.position = new Position();
        this.weight = 100.0f;
        String ANIMATION_FILE = (String) Settings.getValue("enemy_animations");
        this.movingRightAnimation = new Animation("standing_right.png", ANIMATION_FILE, 1, 5);

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

        Collider mainCollider = new CircleCollider("EnemyMainCollider", position, (int) Settings.getValue("enemy_sprite_size"),this);
        colliders.put(mainCollider.getName(), mainCollider);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        state.update(dt);
        colliders.get("EnemyMainCollider").setPosition(position);
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

    @Override
    public void debugRender(Graphics g) {
        colliders.forEach((name, collider) -> collider.render(g));
    }
}
