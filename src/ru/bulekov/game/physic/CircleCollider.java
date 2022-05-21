package ru.bulekov.game.physic;

import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.GameObject;
import ru.bulekov.game.geometry.Position;
import ru.bulekov.game.geometry.Vector2;

import java.awt.*;

public class CircleCollider extends Collider{

    private final float diameter;

    public CircleCollider(String name, Position position, float diameter, GameObject gameObject) {
        super(name, position, gameObject);
        this.diameter = diameter;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval((int) position.getX(), (int) Settings.getValue("WINDOW_HEIGHT") - (int) position.getY(), (int) diameter, (int) diameter);
    }

    @Override
    public void checkCollide(Collider other) {
        if (other instanceof CircleCollider){
            CircleCollider circleCollider = (CircleCollider) other;
            float distance = circleCollider.getPosition().distance(this.position);
            if (distance < (this.diameter/2 + circleCollider.getDiameter()/2)){
                Vector2 velocityVector2 = this.getGameObject().getVelocity().getVelocityVector2();
                this.getGameObject().getVelocity().setVelocityVector2(other.getGameObject().getVelocity().getVelocityVector2());
                other.getGameObject().getVelocity().setVelocityVector2(velocityVector2);
            }
        }
    }

    public float getDiameter() {
        return diameter;
    }

    public String getName(){
        return name;
    }
}
