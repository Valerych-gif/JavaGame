package ru.bulekov.game.gameobject;

import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.input.GameKeyListener;
import ru.bulekov.game.physic.Acceleration;
import ru.bulekov.game.physic.BoxCollider;
import ru.bulekov.game.physic.Collider;
import ru.bulekov.game.physic.Velocity;
import ru.bulekov.game.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;
import static ru.bulekov.game.constants.GameConstants.gravity;

public class Player extends GameObject{

    private final GameKeyListener keyListener;

    public Player(Scene scene) {
        super("Player");
        this.position = new Position(0, 0);
        this.weight = 100000.0f;
        this.keyListener = scene.getKeyListener();

        Collider mainCollider = new BoxCollider("PlayerMainCollider", position, SPRITE_SIZE, SPRITE_SIZE);
        colliders.put(mainCollider.getName(), mainCollider);

        forces.put(gravity.getName(), gravity);
    }

    @Override
    public void update(float dt) {
        if (keyListener.getKeyPressed()[KeyEvent.VK_D]||keyListener.getKeyPressed()[KeyEvent.VK_RIGHT]){
            this.position.setX(position.getX() + dt * 100);
        } else if (keyListener.getKeyPressed()[KeyEvent.VK_A]||keyListener.getKeyPressed()[KeyEvent.VK_LEFT]){
            this.position.setX(position.getX() - dt * 100);
        }

        if (keyListener.getKeyPressed()[KeyEvent.VK_W]||keyListener.getKeyPressed()[KeyEvent.VK_UP]){
            this.position.setY(position.getY() - dt * 100);
        } else if (keyListener.getKeyPressed()[KeyEvent.VK_S]||keyListener.getKeyPressed()[KeyEvent.VK_DOWN]){
            this.position.setY(position.getY() + dt * 100);
        }

        forces.forEach((name, force) -> {
            Vector2 direction = force.getDirection();
            Acceleration tmpAcceleration = new Acceleration(direction, force.getValue()/weight);
            this.acceleration.add(tmpAcceleration);

            Velocity tmpVelocity = new Velocity(acceleration.getAccelerationVector());
            this.velocity.add(tmpVelocity);

            this.position.setX(getPosition().getX()+velocity.getX());
            this.position.setY(getPosition().getY()+velocity.getY());
        });

        colliders.get("PlayerMainCollider").setPosition(position);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) position.getX(), (int) position.getY(), SPRITE_SIZE, SPRITE_SIZE);
    }

    @Override
    public void debugRender(Graphics g) {
        colliders.forEach((name, collider) -> collider.render(g));
    }

    public Position getPosition() {
        return position;
    }
}
