package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Vector2;

public class Acceleration {

    private Vector2 accelerationVector;

    public Acceleration() {
        accelerationVector = new Vector2();
    }

    public Acceleration(Vector2 vector2) {
        this.accelerationVector = vector2;
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
