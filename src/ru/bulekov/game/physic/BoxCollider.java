package ru.bulekov.game.physic;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;

import java.awt.*;

public class BoxCollider extends Collider{

    private int width, height;

    public BoxCollider(String name, Position position, int width, int height, GameObject gameObject) {
        super(name, position, gameObject);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) position.getX(), (int) position.getY(), width, height);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void checkCollide(Collider other) {
        System.out.println(other.getName());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
