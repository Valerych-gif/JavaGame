package ru.bulekov.game.physic.collider;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;

import java.awt.*;

public abstract class Collider {

    protected Position position;
    protected String name;
    protected GameObject gameObject;

    public Collider(String name, Position position, GameObject gameObject) {
        this.name = name;
        this.position = position;
        this.gameObject = gameObject;
    }


    public abstract void render(Graphics g);
    public abstract void checkCollide(Collider other);

    public GameObject getGameObject() {
        return gameObject;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void react(Collider other) {
//        System.out.println(this.getName());
    }
}
