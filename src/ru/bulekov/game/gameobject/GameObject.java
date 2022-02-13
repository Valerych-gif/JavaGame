package ru.bulekov.game.gameobject;

import ru.bulekov.game.gameobject.state.FallingRightState;
import ru.bulekov.game.gameobject.state.State;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.*;
import ru.bulekov.game.render.Animation;
import ru.bulekov.game.scene.GameScene;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class GameObject {

    protected String gameObjectId;
    protected Position position;
    protected Velocity velocity;
    protected Force force;
    protected float weight;
    protected float maxSpeed;

    protected State state;
    protected Animation animation;

    /*
    Possible animations
     */

    protected Animation movingRightAnimation;
    protected Animation movingLeftAnimation;

    /*
    Possible states
     */

    protected State fallingLeftState;
    protected State fallingRightState;
    protected State jumpingLeftState;
    protected State jumpingRightState;
    protected State standingLeftState;
    protected State standingRightState;
    protected State movingLeftState;
    protected State movingRightState;


    protected CollisionsHandler collisionsHandler;
    protected Controller controller;


    protected Map<String, Collider> colliders;
    protected Acceleration acceleration;

    public GameObject(String gameObjectId, Scene scene) {

        this.gameObjectId = gameObjectId;
        this.colliders = new HashMap<>();
        this.velocity = new Velocity();
        this.force = new Force();
        this.controller = new Controller();
        this.collisionsHandler = ((GameScene) scene).getCollisionsHandler();
    }

    public void update(float dt) {
        force = new Force("ForceSum", new Vector2(0, 0));
        collisionsHandler.checkCollides(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public abstract void render(Graphics g);

    public abstract void debugRender(Graphics g);

    public String getGameObjectId() {
        return gameObjectId;
    }

    public Map<String, Collider> getColliders() {
        return colliders;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Force getForce() {
        return force;
    }

    public Position getPosition() {
        return position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Controller getController() {
        return controller;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getWeight() {
        return weight;
    }

    public State getStandingLeftState() {
        return standingLeftState;
    }

    public State getState() {
        return state;
    }

    public State getFallingLeftState() {
        return fallingLeftState;
    }

    public State getFallingRightState() {
        return fallingRightState;
    }

    public State getJumpingLeftState() {
        return jumpingLeftState;
    }

    public State getJumpingRightState() {
        return jumpingRightState;
    }

    public State getStandingRightState() {
        return standingRightState;
    }

    public State getMovingLeftState() {
        return movingLeftState;
    }

    public State getMovingRightState() {
        return movingRightState;
    }

    public void setAcceleration(Acceleration acceleration) {
        this.acceleration = acceleration;
    }

    public Acceleration getAcceleration() {
        return this.acceleration;
    }

    public Animation getAnimation(){
        return animation;
    }
}
