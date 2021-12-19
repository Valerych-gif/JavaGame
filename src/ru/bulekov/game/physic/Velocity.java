package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Vector2;

public class Velocity {
    private Vector2 direction;
    private float value;

    private Vector2 velocityVector2;

    public Velocity() {
        direction = new Vector2();
        velocityVector2 = new Vector2();
        value = 0;
    }

    public Velocity(Vector2 velocityVector2) {
        this.velocityVector2 = velocityVector2;
    }

    public Velocity(Vector2 direction, float value) {
        this.direction = direction;
        this.value = value;
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

    public Vector2 getVelocityVector2() {
        return velocityVector2;
    }

    public void setVelocityVector2(Vector2 velocityVector2) {
        this.velocityVector2 = velocityVector2;
    }

    public float getX(){
        return velocityVector2.getX();
    }

    public float getY() {
        return velocityVector2.getY();
    }

    public void add(Velocity velocity) {
        velocityVector2.add(velocity.velocityVector2);
    }

}
