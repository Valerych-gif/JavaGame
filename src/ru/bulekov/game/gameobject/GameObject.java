package ru.bulekov.game.gameobject;

import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.physic.Acceleration;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.physic.Force;
import ru.bulekov.game.physic.Velocity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class GameObject {

    protected String gameObjectId;
    protected Position position;
    protected Velocity velocity;
    protected Acceleration acceleration;
    protected float weight;

    protected Map<String, Collider> colliders;
    protected Map<String, Force> forces;

    public GameObject(String gameObjectId) {
        this.gameObjectId = gameObjectId;
        this.colliders = new HashMap<>();
        this.forces = new HashMap<>();
        this.velocity = new Velocity();
        this.acceleration = new Acceleration();
    }

    public abstract void update(float dt);
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
}
