package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;
import ru.bulekov.game.physic.Velocity;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.accuracy;

public class StandingRightState extends State {
    public StandingRightState(GameObject gameObject) {
        super(gameObject);
        this.animation = gameObject.getAnimation();
    }

    @Override
    public void update(float dt) {

        Velocity velocity = gameObject.getVelocity();
        if (velocity.getX() > accuracy) {
            gameObject.setState(gameObject.getMovingRightState());
        } else if (velocity.getX() < -accuracy) {
            gameObject.setState(gameObject.getMovingLeftState());
        } else {
            gameObject.getVelocity().setVelocityVector2(new Vector2());
        }

        if (gameObject.getController().isGoingRight()) {
            Force rightForce = new Force("RightForce", new Vector2(4, 0));
            gameObject.getForce().add(rightForce);
            System.out.println("Set state to Moving Right");
            gameObject.setState(gameObject.getMovingRightState());
        } else if (gameObject.getController().isGoingLeft()) {
            Force leftForce = new Force("LeftForce", new Vector2(-4, 0));
            gameObject.getForce().add(leftForce);
            System.out.println("Set state to Moving Left");
            gameObject.setState(gameObject.getMovingLeftState());
        }
        if (gameObject.getController().isJump()) {
            gameObject.getVelocity().getVelocityVector2().add(new Vector2(0, 2));
            System.out.println("Set state to Jump Right");
            gameObject.setState(gameObject.getJumpingRightState());
        }

        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
