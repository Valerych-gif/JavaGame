package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Acceleration;
import ru.bulekov.game.physic.Velocity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static ru.bulekov.game.gameobject.state.States.*;

public abstract class State {

    public static final int JUMP = 0;
    public static final int WALK = 1;
    public static final int STOP = 2;
    public static final int TURN_RIGHT = 3;
    public static final int TURN_LEFT = 4;
    public static final int FALL = 5;
    public static final int GROUND_TOUCH = 6;


    public State() {

    }

    protected void setPosition(GameObject gameObject){
        Vector2 forceVector2 = gameObject.getForce().getForceVector2();

        Acceleration acceleration = new Acceleration(new Vector2(forceVector2.getX() / gameObject.getWeight(), forceVector2.getY() / gameObject.getWeight()));

        Velocity velocityChange = new Velocity(acceleration.getAccelerationVector());
        gameObject.getVelocity().add(velocityChange);
        Velocity velocity = gameObject.getVelocity();

        Position position = gameObject.getPosition();
        position.setX(position.getX() + velocity.getX());
        position.setY(position.getY() + velocity.getY());
    }

    public abstract void update(GameObject gameObject, float dt);
    public abstract void changeState(GameObject gameObject, int action);
    public abstract void render(GameObject gameObject, Graphics g);
}
