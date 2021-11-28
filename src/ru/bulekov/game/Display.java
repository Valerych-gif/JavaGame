package ru.bulekov.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

import static ru.bulekov.game.constants.GameConstants.TITLE;


public class Display extends JFrame {

    private Canvas canvas;

    public Display(int width, int height){
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        add(canvas);
        pack();

        canvas.createBufferStrategy(3);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(){
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        // clear display
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        graphics.dispose();
        bufferStrategy.show();
    }
}
