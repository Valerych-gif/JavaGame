package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;

import java.awt.*;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;
import static ru.bulekov.game.constants.GameConstants.gravity;

public class FallingRight extends State {
    public FallingRight() {
        super();
    }

    @Override
    public void update(GameObject gameObject, float dt) {
        gameObject.getForce().add(gravity);
        super.setPosition(gameObject);
    }

    @Override
    public void changeState(GameObject gameObject, int action) {
        switch (action){
            case TURN_LEFT:
                gameObject.setState(new FallingLeft());
                break;
            case GROUND_TOUCH:
                gameObject.setState(new StandingRight());
            default:
                break;
        }
    }

    @Override
    public void render(GameObject gameObject, Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) gameObject.getPosition().getX(), (int) gameObject.getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE);
    }
}
