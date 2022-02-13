package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static ru.bulekov.game.constants.GameConstants.gravity;

public class JumpingLeftState extends State {
    public JumpingLeftState(GameObject gameObject) {
        super(gameObject);
        this.animation = gameObject.getAnimation();
    }

    @Override
    public void update(float dt) {
        gameObject.getForce().add(gravity);
        if (gameObject.getVelocity().getY()<0){
            System.out.println("Set state to Falling Left");
            gameObject.setState(gameObject.getFallingLeftState());
        }
        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
