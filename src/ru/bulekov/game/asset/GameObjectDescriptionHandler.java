package ru.bulekov.game.asset;

import ru.bulekov.game.config.asset.AssetLoader;
import ru.bulekov.game.gameobject.ObjectDescription;

public class GameObjectDescriptionHandler {

    public static ObjectDescription get(String objectName, ObjectDescription objectDescription) {
        return AssetLoader.getObject(objectName, objectDescription);
    }
}
