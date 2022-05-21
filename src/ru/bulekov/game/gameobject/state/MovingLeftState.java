package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.accuracy;

public class MovingLeftState extends State {

    public MovingLeftState(GameObject gameObject) {
        super(gameObject);

        this.animation = gameObject.getAnimation();
    }

    @Override
    public void update(float dt) {

        Force friction = new Force("Friction", new Vector2(0.5f, 0));
        float horizontalSpeed = gameObject.getVelocity().getVelocityVector2().getX();

        if (horizontalSpeed < -accuracy) {
            gameObject.getForce().add(friction);
        } else {
            gameObject.getForce().sub(friction);
            gameObject.getVelocity().setVelocityVector2(new Vector2());
            System.out.println("Set state to Standing Left");
            gameObject.setState(gameObject.getStandingLeftState());
        }

        if (gameObject.getController().isGoingRight() && horizontalSpeed < gameObject.getMaxSpeed()) {
            Force rightForce = new Force("RightForce", new Vector2(4, 0));
            gameObject.getForce().add(rightForce);
            System.out.println("Set state to Moving Right");
            gameObject.setState(gameObject.getMovingRightState());
        } else if (gameObject.getController().isGoingLeft() && horizontalSpeed > -gameObject.getMaxSpeed()) {
            Force leftForce = new Force("LeftForce", new Vector2(-4, 0));
            gameObject.getForce().add(leftForce);
        }
        if (gameObject.getController().isJump()){
            gameObject.getVelocity().getVelocityVector2().add(new Vector2(0, 2));
            System.out.println("Set state to Jump Left");
            gameObject.setState(gameObject.getJumpingLeftState());
        }
        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
