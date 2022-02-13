package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Vector2;

public class Force {

    private String name;
    private Vector2 forceVector2;

    public Force() {
        this.name = "";
        this.forceVector2 = new Vector2(0, 0);
    }

    public Force(String name, Vector2 forceVector2) {
        this.name = name;
        this.forceVector2 = forceVector2;
        this.forceVector2.setY(this.forceVector2.getY());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2 getForceVector2() {
        return forceVector2;
    }

    public void setForceVector2(Vector2 forceVector2) {
        this.forceVector2 = forceVector2;
        this.forceVector2.setY(this.forceVector2.getY());
    }

    public void add(Force force) {
        forceVector2.add(force.getForceVector2());
    }

    public void sub(Force force) {
        forceVector2.sub(new Vector2(force.getForceVector2().getX(), force.getForceVector2().getY()));
    }
}
