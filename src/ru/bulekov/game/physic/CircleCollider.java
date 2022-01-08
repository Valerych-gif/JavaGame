package ru.bulekov.game.physic;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;

import java.awt.*;

public class CircleCollider extends Collider{

    private float radius;

    public CircleCollider(String name, Position position, float radius, GameObject gameObject) {
        super(name, position, gameObject);
        this.radius = radius;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval((int) position.getX(), (int) position.getY(), (int) radius, (int) radius);
    }

    @Override
    public void collide(Collider other) {

    }

    public float getRadius() {
        return radius;
    }

    public String getName(){
        return name;
    }
}
