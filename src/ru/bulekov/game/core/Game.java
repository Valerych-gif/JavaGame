package ru.bulekov.game.core;

import ru.bulekov.game.Display;

import java.awt.*;

public class Game {
    private Display display;

    public Game(int width, int height) {
        this.display = new Display(width, height);
    }

    public void update(float dt){

    }

    public void render(){
        display.render();
    }
}
