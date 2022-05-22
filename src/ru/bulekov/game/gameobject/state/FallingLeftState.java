package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.gravity;

public class FallingLeftState extends State {

    public FallingLeftState(GameObject gameObject) {
        super(gameObject, "standing_right");
    }

    @Override
    public void update(float dt) {
        gameObject.getForce().add(gravity);
        if (gameObject.getPosition().getY() <= 100) {
            gameObject.setPosition(new Position(new Vector2(gameObject.getPosition().getX(), 100)));
            gameObject.getVelocity().setVelocityVector2(new Vector2(gameObject.getVelocity().getX(), 0));
            gameObject.getForce().sub(gravity);
            System.out.println("Set state to Moving Left");
            gameObject.setCurrentState(gameObject.getMovingLeftState());
        }
        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
