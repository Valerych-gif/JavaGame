package ru.bulekov.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    boolean[] keyPressed = new boolean[256];
    boolean[] keySwitched = new boolean[256];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed[e.getKeyCode()] = true;
        keySwitched[e.getKeyCode()] = !keySwitched[e.getKeyCode()];
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed[e.getKeyCode()] = false;
    }

    public boolean[] getKeyPressed() {
        return keyPressed;
    }

    public boolean[] getKeySwitchedOn() {
        return keySwitched;
    }

    public void init() {
    }
}
