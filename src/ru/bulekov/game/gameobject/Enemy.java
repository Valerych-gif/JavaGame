package ru.bulekov.game.gameobject;

import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.physic.BoxCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.scene.Scene;

import java.awt.*;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;

public class Enemy extends GameObject{

    private Position position;

    public Enemy(Scene scene) {
        super("Enemy");
        this.position = new Position(100, 100);

        Collider mainCollider = new BoxCollider("EnemyMainCollider", position, SPRITE_SIZE, SPRITE_SIZE);
        colliders.put(mainCollider.getName(), mainCollider);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) position.getX(), (int) position.getY(), SPRITE_SIZE, SPRITE_SIZE);
    }

    @Override
    public void debugRender(Graphics g) {
        colliders.forEach((name, collider) -> collider.render(g));
    }
}
