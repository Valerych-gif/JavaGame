package ru.bulekov.game.gameobject;

import lombok.Data;
import ru.bulekov.game.asset.AssetsHandler;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.state.State;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.*;
import ru.bulekov.game.physic.collider.Collider;
import ru.bulekov.game.render.Animation;
import ru.bulekov.game.render.AnimationSettings;
import ru.bulekov.game.scene.GameScene;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class GameObject {

    protected Settings settings;
    protected ObjectDescription description;

    protected GameKeyListener keyListener;
    protected AssetsHandler assetsHandler;

    protected String gameObjectId;
    protected Position position;
    protected Velocity velocity;
    protected Force force;
    protected float weight;
    protected float maxSpeed;

    protected State currentState;
    protected Animation animation;

    /*
    Possible animations
     */

    protected List<AnimationSettings> animationSettings;
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

    public GameObject() {
    }

    public GameObject(String gameObjectId, Scene scene) {

        this.assetsHandler = scene.getGame().getAssetsHandler();
        this.keyListener = scene.getKeyListener();
        this.settings = Settings.getInstance();
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

    public abstract void render(Graphics g);
    public abstract void debugRender(Graphics g);
}
