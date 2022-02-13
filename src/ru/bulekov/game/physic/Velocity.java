package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Vector2;

public class Velocity {

    private Vector2 velocityVector2;

    public Velocity() {
        velocityVector2 = new Vector2();
    }

    public Velocity(Vector2 velocityVector2) {
        this.velocityVector2 = velocityVector2;
        this.velocityVector2.setY(this.velocityVector2.getY());
    }

    public Vector2 getVelocityVector2() {
        return velocityVector2;
    }

    public void setVelocityVector2(Vector2 velocityVector2) {
        this.velocityVector2 = velocityVector2;
        this.velocityVector2.setY(this.velocityVector2.getY());
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
