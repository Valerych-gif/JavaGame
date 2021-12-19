package ru.bulekov.game.geometry;

public class Position {
    float x, y;
    float centerX, centerY;
    float worldX, worldY;
    int gridX, gridY;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
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
}
