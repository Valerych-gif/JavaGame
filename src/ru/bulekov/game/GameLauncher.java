package ru.bulekov.game;

import ru.bulekov.game.core.Game;
import ru.bulekov.game.core.GameLoop;

import static ru.bulekov.game.constants.GameConstants.WINDOW_HEIGHT;
import static ru.bulekov.game.constants.GameConstants.WINDOW_WIDTH;

public class GameLauncher {

    public static void main(String[] args) {

        Game game = new Game(WINDOW_WIDTH, WINDOW_HEIGHT);

        new Thread(new GameLoop(game)).start();
    }
}
