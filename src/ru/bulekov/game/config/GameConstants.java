package ru.bulekov.game.config;

import ru.bulekov.game.geometry.Vector2;
import ru.bulekov.game.physic.Force;

public interface GameConstants {

    Force gravity = new Force("Gravity", new Vector2(0f, -0.9f));

    String SETTINGS_FILE = "data/config/settings.json";

    boolean debugMode = true;

    double accuracy = 0.0000001;
}
