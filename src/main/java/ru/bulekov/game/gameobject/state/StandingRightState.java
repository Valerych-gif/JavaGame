package ru.bulekov.game.gameobject.state;

import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;
import ru.bulekov.game.physic.Velocity;
import ru.bulekov.game.render.Animation;
import ru.bulekov.game.render.AnimationSettings;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.accuracy;

@Slf4j
public class StandingRightState extends State {

    public StandingRightState(GameObject gameObject) {
        super(gameObject, "standing_right");
    }

    @Override
    public void update(float dt) {

        if (gameObject.getController().isGoingRight()) {
            Force rightForce = new Force("RightForce", new Vector2(4, 0));
            gameObject.getForce().add(rightForce);
            log.info("Set state to Moving Right");
            gameObject.setCurrentState(gameObject.getMovingRightState());
        } else if (gameObject.getController().isGoingLeft()) {
            Force leftForce = new Force("LeftForce", new Vector2(-4, 0));
            gameObject.getForce().add(leftForce);
            log.info("Set state to Moving Left");
            gameObject.setCurrentState(gameObject.getMovingLeftState());
        }
        if (gameObject.getController().isJump()) {
            gameObject.getVelocity().getVelocityVector2().add(new Vector2(0, 2));
            log.info("Set state to Jump Right");
            gameObject.setCurrentState(gameObject.getJumpingRightState());
        }

        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
