package ru.bulekov.game.asset;

import ru.bulekov.game.gameobject.ObjectDescription;

public class GameObjectDescriptionHandler {

    public ObjectDescription get(AssetsHandler assetsHandler, String objectName) {
        return assetsHandler.getCharacter(objectName);
    }
}
