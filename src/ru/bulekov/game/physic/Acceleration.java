package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Vector2;

public class Acceleration {

    private Vector2 direction;
    private Vector2 accelerationVector;
    private float value;

    public Acceleration() {
        direction = new Vector2();
        accelerationVector = new Vector2();
        value = 0;
    }

    public Acceleration(Vector2 vector2) {
        this.accelerationVector = vector2;
    }

    public Acceleration(Vector2 direction, float value) {
        this.direction = direction;
        this.value = value;
        this.accelerationVector = new Vector2(direction.getX() * value, direction.getY() * value);
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

    public Vector2 getAccelerationVector() {
        return accelerationVector;
    }

    public void setAccelerationVector(Vector2 accelerationVector) {
        this.accelerationVector = accelerationVector;
    }

    public void add(Acceleration acceleration) {
        accelerationVector.add(acceleration.getAccelerationVector());
    }
}
