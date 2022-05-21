package ru.bulekov.game.core;


import ru.bulekov.game.config.Settings;

public class GameLoop implements Runnable {

    private static final int MILLIS_PER_SECOND = 1_000;
    private static final int NANOS_PER_SECOND = 1_000_000_000;

    private final int UPS = (int) Settings.getValue("UPS");
    private final int FPS = (int) Settings.getValue("FPS");
    private final int USPS = (int) Settings.getValue("USPS");

    private final double updateRate = (double) (1 / UPS) * NANOS_PER_SECOND;
    private final double renderRate = (double) (1 / FPS) * NANOS_PER_SECOND;
    private final double updateStatsRate = (double) 1 / USPS;

    private boolean isRunning = true;

    private long nextUpdateStatsTime = (long) (System.currentTimeMillis() + MILLIS_PER_SECOND * updateStatsRate);
    private int fps, ups;
    float dt;

    long lastUpdateTime, updateDelta, lastRenderTime, renderDelta;

    private final Game game;

    public GameLoop(Game game) {
        this.game = game;
    }


    @Override
    public void run() {
        System.out.println("Game starting...");
        init();

        lastUpdateTime = System.nanoTime();
        lastRenderTime = System.nanoTime();

        while (isRunning) {

            update();
            render();
            printStats();

        }

        stopGame();
    }


    private void update() {
        updateDelta = System.nanoTime() - lastUpdateTime;
        if (updateDelta >= updateRate) {
            lastUpdateTime = System.nanoTime();
            dt = updateDelta / (float) NANOS_PER_SECOND;
            ups++;
            game.update(dt);
        }
    }

    private void render() {
        renderDelta = System.nanoTime() - lastRenderTime;
        if (renderDelta >= renderRate) {
            lastRenderTime = System.nanoTime();
            fps++;
            game.render();
        }
    }

    private void printStats() {
        if (System.currentTimeMillis() >= nextUpdateStatsTime){
            nextUpdateStatsTime = (long) (System.currentTimeMillis() + MILLIS_PER_SECOND * updateStatsRate);
            System.out.println("FPS: " + (fps * USPS) + " UPS: " + (ups * USPS));
            fps = 0;
            ups = 0;
        }
    }

    private void stopGame() {
    }

    private void init() {
    }


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
