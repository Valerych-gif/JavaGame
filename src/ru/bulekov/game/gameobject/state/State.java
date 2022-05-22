package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Acceleration;
import ru.bulekov.game.physic.Velocity;
import ru.bulekov.game.render.Animation;
import ru.bulekov.game.render.AnimationSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class State {

    protected final GameObject gameObject;
    protected Animation animation;
    private int frameNumber;
    protected long frameDuration;
    protected List<BufferedImage> frames;
    private long lastFrameTimestamp;
    protected Settings settings;

    public State(GameObject gameObject, String state) {
        this.settings = Settings.getInstance();
        this.gameObject = gameObject;
        this.animation = new Animation(gameObject, getAnimationSettings(state));
        this.frames = animation.getFrames();
        this.frameDuration = 1_000 / animation.getFramesPerSecond();
        this.lastFrameTimestamp = System.currentTimeMillis();
    }

    private AnimationSettings getAnimationSettings(String state) {
        return AnimationSettings.builder()
                .animationName(state)
                .fileName("animations.json")
                .frameWidth((int) gameObject.getDescription().getWidth())
                .framesPerSecond((int) settings.getValue("frames_per_second"))
                .build();
    }

    protected void physicCalculate() {
        gameObject.setAcceleration(getAcceleration());
        setVelocity();
        setPosition();
    }

    private void setPosition() {
        Velocity velocity = gameObject.getVelocity();
        Position position = gameObject.getPosition();
        float velocityX = velocity.getX();
        float velocityY = velocity.getY();
        position.setX(position.getX() + velocityX);
        position.setY(position.getY() + velocityY);
    }

    private void setVelocity() {
        Velocity velocityChange = new Velocity(gameObject.getAcceleration().getAccelerationVector());
        gameObject.getVelocity().add(velocityChange);
    }

    private Acceleration getAcceleration() {
        Vector2 forceVector2 = gameObject.getForce().getForceVector2();
        return new Acceleration(new Vector2(forceVector2.getX() / gameObject.getWeight(), forceVector2.getY() / gameObject.getWeight()));
    }

    public abstract void update(float dt);

    public void render(Graphics g) {
        long currentTimestamp = System.currentTimeMillis();
        long delta = currentTimestamp - lastFrameTimestamp;
        if (delta > frameDuration) {
            lastFrameTimestamp = currentTimestamp;
            frameNumber++;
            if (frameNumber >= frames.size()) frameNumber = 0;
        }
        g.drawImage(
                frames.get(frameNumber),
                (int) gameObject.getPosition().getX(),
                (int) settings.getValue("WINDOW_HEIGHT") - (int) gameObject.getPosition().getY(),
                null);
        gameObject.debugRender(g);
    }
}
