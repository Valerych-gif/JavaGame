package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;

import java.awt.*;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;

public class StandingRight extends State {

    public StandingRight() {
        super();
    }

    @Override
    public void update(GameObject gameObject, float dt) {

        if (gameObject.getController().isGoingRight()) {
            Force rightForce = new Force("RightForce", new Vector2(4, 0));
            gameObject.getForce().add(rightForce);
            gameObject.setState(new WalkingRight());
        } else if (gameObject.getController().isGoingLeft()) {
            Force leftForce = new Force("LeftForce", new Vector2(-4, 0));
            gameObject.getForce().add(leftForce);
            gameObject.setState(new WalkingLeft());
        }
        super.setPosition(gameObject);
    }

    public void changeState(GameObject gameObject, int action){

        gameObject.getVelocity().getVelocityVector2().setY(0);

        switch (action){
            case JUMP:
                gameObject.setState(new JumpingRight());
                break;
            case WALK:
                gameObject.setState(new WalkingRight());
                break;
            case TURN_LEFT:
                gameObject.setState(new StandingLeft());
                break;
            case FALL:
                gameObject.setState(new FallingRight());
            default:
                break;
        }
    }

    @Override
    public void render(GameObject gameObject, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) gameObject.getPosition().getX(), (int) gameObject.getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE);
    }
}
