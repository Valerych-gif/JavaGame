package ru.bulekov.game.constants;

import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;

public interface GameConstants {

    String TITLE = "Game";
    int WINDOW_WIDTH = 1024;
    int WINDOW_HEIGHT = 720;
    int SPRITE_SIZE = 64;

    double FPS = 60d;
    double UPS = 240d;
    double USPS = 1d;

    Force gravity = new Force("Gravity", new Vector2(0f, -0.9f));

    boolean debugMode = true;

    double accuracy = 0.0000001;
}
