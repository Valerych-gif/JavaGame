package ru.bulekov.game.physic;

import lombok.Data;
import ru.bulekov.game.geometry.Vector2;

@Data
public class Acceleration {

    private Vector2 accelerationVector;

    public Acceleration() {
        accelerationVector = new Vector2();
    }

    public Acceleration(Vector2 vector2) {
        this.accelerationVector = vector2;
    }

    public void add(Acceleration acceleration) {
        accelerationVector.add(acceleration.getAccelerationVector());
    }
}
