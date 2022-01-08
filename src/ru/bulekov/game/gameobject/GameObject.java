package ru.bulekov.game.gameobject;

import ru.bulekov.game.gameobject.state.FallingRight;
import ru.bulekov.game.gameobject.state.StandingRight;
import ru.bulekov.game.gameobject.state.State;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.*;
import ru.bulekov.game.scene.GameScene;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static ru.bulekov.game.constants.GameConstants.WINDOW_HEIGHT;
import static ru.bulekov.game.gameobject.state.State.GROUND_TOUCH;

public abstract class GameObject {

    protected String gameObjectId;
    protected Position position;
    protected Velocity velocity;
    protected Force force;
    protected float weight;
    protected float maxSpeed;

    protected State state;

    protected boolean onGround;

    protected CollisionsHandler collisionsHandler;
    protected Controller controller;


    protected Map<String, Collider> colliders;

    public GameObject(String gameObjectId, Scene scene) {
        this.gameObjectId = gameObjectId;
        this.colliders = new HashMap<>();
        this.velocity = new Velocity();
        this.force = new Force();
        this.controller = new Controller();
        this.collisionsHandler = ((GameScene) scene).getCollisionsHandler();
        this.state = new FallingRight();
    }

    public void update(float dt) {

        force = new Force("ForceSum", new Vector2(0, 0));
        if (position.getY() >= WINDOW_HEIGHT - 100) {
            state.changeState(this, GROUND_TOUCH);
        }
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

    public void setState(State state) {
        this.state = state;
    }

    public float getWeight() {
        return weight;
    }
}
