package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Vector2;

public class Force {

    private String name;
    private Vector2 direction;
    private float value;
    private Vector2 forceVector2;

    public Force() {
    }

    public Force(Vector2 forceVector2) {
        this.forceVector2 = forceVector2;
    }

    public Force(String name, Vector2 direction, float value) {
        this.name = name;
        this.direction = direction;
        this.value = value;
        this.forceVector2 = new Vector2(direction.getX() * value, direction.getY() * value);
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2 getForceVector2() {
        return forceVector2;
    }

    public void setForceVector2(Vector2 forceVector2) {
        this.forceVector2 = forceVector2;
    }

    public void add(Force force) {
        forceVector2.add(force.getForceVector2());
    }
}
