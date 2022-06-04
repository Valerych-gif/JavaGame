package ru.bulekov.game;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.core.GameLoop;

public class GameLauncher {

    public static void main(String[] args) {
        new Thread(new GameLoop(new Game())).start();
    }
}
