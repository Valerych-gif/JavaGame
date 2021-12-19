package ru.bulekov.game.physic;

import ru.bulekov.game.geometry.Position;

import java.awt.*;

public interface Collider {
    void render(Graphics g);
    String getName();
    void setPosition(Position position);
    void collide(Collider other);
    void reaction(ColliderCallback callback);
//    void collideReaction(ColliderCallback callback);
}
