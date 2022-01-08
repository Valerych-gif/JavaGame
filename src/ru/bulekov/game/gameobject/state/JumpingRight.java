package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.gameobject.player.Player;

import java.awt.*;

import static ru.bulekov.game.constants.GameConstants.SPRITE_SIZE;

public class JumpingRight extends State {
    public JumpingRight() {
        super();
    }

    @Override
    public void update(GameObject gameObject, float dt) {
        super.setPosition(gameObject);
    }

    @Override
    public void changeState(GameObject gameObject, int action) {

    }

    @Override
    public void render(GameObject gameObject, Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) gameObject.getPosition().getX(), (int) gameObject.getPosition().getY(), SPRITE_SIZE, SPRITE_SIZE);
    }
}
