package ru.bulekov.game.geometry;

public class Position {

    private final Vector2 positionVector2;

    float centerX, centerY;
    float worldX, worldY;
    int gridX, gridY;

    public Position(){
        this(new Vector2(0,0));
    }

    public Position(Vector2 positionVector2) {
        this.positionVector2 = new Vector2();
        setX(positionVector2.getX());
        setY(positionVector2.getY());
    }

    public float getX() {
        return positionVector2.getX();
    }

    public void setX(float x) {
        this.positionVector2.setX(x);
    }

    public float getY() {
        return this.positionVector2.getY() ;
    }

    public void setY(float y) {
        this.positionVector2.setY(y);
    }

    public float distance(Position position) {
        float dX = position.getX() - this.positionVector2.getX();
        float dY = position.getY() - this.positionVector2.getY();
        return (float) Math.sqrt(dX * dX + dY * dY);
    }
}
