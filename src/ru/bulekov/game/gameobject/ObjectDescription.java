package ru.bulekov.game.gameobject;

import ru.bulekov.game.render.Animation;

public interface ObjectDescription {
    Animation getAnimation(String standing_right);
}
