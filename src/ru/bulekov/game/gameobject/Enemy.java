package ru.bulekov.game.gameobject;

import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.physic.CircleCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.scene.Scene;

import java.awt.*;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;

public class Enemy extends GameObject{

    public Enemy(String name, Scene scene) {
        super(name, scene);
        this.position = new Position();
        this.weight = 100.0f;

        Collider mainCollider = new CircleCollider("EnemyMainCollider", position, SPRITE_SIZE,this);
        colliders.put(mainCollider.getName(), mainCollider);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        state.update(this, dt);
        colliders.get("EnemyMainCollider").setPosition(position);
    }

    @Override
    public void render(Graphics g) {
        state.render(this, g);
    }

    @Override
    public void debugRender(Graphics g) {
        colliders.forEach((name, collider) -> collider.render(g));
    }
}
