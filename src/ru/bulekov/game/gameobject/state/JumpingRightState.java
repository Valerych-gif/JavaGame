package ru.bulekov.game.gameobject.state;

import ru.bulekov.game.gameobject.GameObject;

import java.awt.*;

import static ru.bulekov.game.config.GameConstants.gravity;

public class JumpingRightState extends State {
    public JumpingRightState(GameObject gameObject) {
        super(gameObject);
        this.animation = gameObject.getAnimation();
    }

    @Override
    public void update(float dt) {
        gameObject.getForce().add(gravity);
        if (gameObject.getVelocity().getY()<0){
            System.out.println("Set state to Falling Right");
            gameObject.setState(gameObject.getFallingRightState());
        }
        super.physicCalculate();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
