package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;

import java.awt.*;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;

public class WalkingRight extends State {
    public WalkingRight() {
        super();
    }

    @Override
    public void update(GameObject gameObject, float dt) {
        float horizontalSpeed = gameObject.getVelocity().getVelocityVector2().getX();

        if (horizontalSpeed > 0.0001f) {
            Force friction = new Force("Friction", new Vector2(-1, 0));
            gameObject.getForce().add(friction);
        } else {
            gameObject.setState(new StandingRight());
        }

        if (gameObject.getController().isGoingRight() && horizontalSpeed < gameObject.getMaxSpeed()) {
            Force rightForce = new Force("RightForce", new Vector2(4, 0));
            gameObject.getForce().add(rightForce);
        } else if (gameObject.getController().isGoingLeft() && horizontalSpeed > -gameObject.getMaxSpeed()) {
            Force leftForce = new Force("LeftForce", new Vector2(-4, 0));
            gameObject.getForce().add(leftForce);
        }
        super.setPosition(gameObject);
    }

    @Override
    public void changeState(GameObject gameObject, int action) {
        System.out.println("Walking right");
    }

    @Override
    public void render(GameObject gameObject, Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) gameObject.getPosition().getX(), (int) gameObject.getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE);
    }
}
