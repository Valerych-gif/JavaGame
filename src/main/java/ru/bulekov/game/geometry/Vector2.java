package ru.bulekov.game.geometry;

public class Vector2 {
    private float x, y;

    public Vector2() {
        new Vector2(0, 0);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 vector2) {
        float dX = vector2.getX();
        float dY = vector2.getY();
        this.x += dX;
        this.y += dY;
    }

    public Vector2 normalize() {
        Vector2 normalizedVector2 = new Vector2(x, y);
        normalizedVector2.setX(x / getLength());
        normalizedVector2.setY(y / getLength());

        return normalizedVector2;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getLength() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void sub(Vector2 vector2) {
        this.x -= vector2.getX();
        this.y -= vector2.getY();
    }
}
