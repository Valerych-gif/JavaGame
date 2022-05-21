package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.gravity;

public class FallingRightState extends State {

    public FallingRightState(GameObject gameObject) {
        super(gameObject);
        this.animation = gameObject.getAnimation();
    }

    @Override
    public void update(float dt) {
        gameObject.getForce().add(gravity);
        float gameObjectPositionY = gameObject.getPosition().getY();
        if (gameObjectPositionY <= 100) {
            gameObject.setPosition(new Position(new Vector2(gameObject.getPosition().getX(), 100)));
            gameObject.getVelocity().setVelocityVector2(new Vector2(gameObject.getVelocity().getX(), 0));
            gameObject.getForce().sub(gravity);
            System.out.println("Set state to Moving Right");
            gameObject.setState(gameObject.getMovingRightState());
        }
        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
