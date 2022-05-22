package ru.bulekov.game.physic;

import lombok.Data;
import ru.bulekov.game.geometry.Vector2;

@Data
public class Velocity {

    private Vector2 velocityVector2;

    public Velocity() {
        this.velocityVector2 = new Vector2();
    }

    public Velocity(Vector2 velocityVector2) {
        this.velocityVector2 = new Vector2();
        this.velocityVector2.setX(velocityVector2.getX());
        this.velocityVector2.setY(velocityVector2.getY());
    }

    public float getX(){
        return velocityVector2.getX();
    }

    public float getY() {
        return velocityVector2.getY();
    }

    public void add(Velocity velocity) {
        this.velocityVector2.add(velocity.velocityVector2);
    }

}
