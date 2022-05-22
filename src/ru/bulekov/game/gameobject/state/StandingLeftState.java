package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.accuracy;

public class StandingLeftState extends State {
    public StandingLeftState(GameObject gameObject) {
        super(gameObject, "standing_right");
        this.animation = gameObject.getAnimation();
    }

    @Override
    public void update(float dt) {
        float vX = gameObject.getVelocity().getX();
        if (vX > accuracy) {
            gameObject.setCurrentState(gameObject.getMovingRightState());
        } else if (vX < -accuracy) {
            gameObject.setCurrentState(gameObject.getMovingLeftState());
        } else {
            gameObject.getVelocity().setVelocityVector2(new Vector2());
        }
        if (gameObject.getController().isGoingRight()) {
            Force rightForce = new Force("RightForce", new Vector2(4, 0));
            gameObject.getForce().add(rightForce);
            System.out.println("Set state to Moving Right");
            gameObject.setCurrentState(gameObject.getMovingRightState());
        } else if (gameObject.getController().isGoingLeft()) {
            Force leftForce = new Force("LeftForce", new Vector2(-4, 0));
            gameObject.getForce().add(leftForce);
            System.out.println("Set state to Moving Right");
            gameObject.setCurrentState(gameObject.getMovingLeftState());
        }
        if (gameObject.getController().isJump()){
            gameObject.getVelocity().getVelocityVector2().add(new Vector2(0, 2));
            System.out.println("Set state to Jump Left");
            gameObject.setCurrentState(gameObject.getJumpingLeftState());
        }
        if (gameObject.getVelocity().getX()!=0){
            gameObject.setCurrentState(gameObject.getMovingRightState());
        }

        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
