package ru.bulekov.game.core;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.config.Settings;

@Data
@Slf4j
public class GameLoop implements Runnable {

    private static final int MILLIS_PER_SECOND = 1_000;
    private static final int NANOS_PER_SECOND = 1_000_000_000;

    private final Settings settings;

    private final double UPS, FPS, USPS;

    private boolean isRunning = true;

    private long nextUpdateStatsTime = System.currentTimeMillis() + MILLIS_PER_SECOND;
    private int fps, ups;
    float dt;

    long lastUpdateTime, updateDelta, lastRenderTime, renderDelta;

    private final Game game;

    public GameLoop(Game game) {
        this.game = game;
        this.settings = game.getSettings();
        this.UPS = (double) settings.getValue("UPS");
        this.FPS = (double) settings.getValue("FPS");
        this.USPS = (double) settings.getValue("USPS");
    }


    @Override
    public void run() {

        double updateRate = (1 / UPS) * NANOS_PER_SECOND;
        double renderRate = (1 / FPS) * NANOS_PER_SECOND;
        double updateStatsRate = (double) 1 / USPS;

        log.info("Game starting...");

        lastUpdateTime = System.nanoTime();
        lastRenderTime = System.nanoTime();

        while (isRunning) {
            update(updateRate);
            render(renderRate);
            printStats(updateStatsRate);
        }

        stopGame();
    }


    private void update(double updateRate) {
        updateDelta = System.nanoTime() - lastUpdateTime;
        if (updateDelta >= updateRate) {
            lastUpdateTime = System.nanoTime();
            dt = updateDelta / (float) NANOS_PER_SECOND;
            ups++;
            game.update(dt);
        }
    }

    private void render(double renderRate) {
        renderDelta = System.nanoTime() - lastRenderTime;
        if (renderDelta >= renderRate) {
            lastRenderTime = System.nanoTime();
            fps++;
            game.render();
        }
    }

    private void printStats(double updateStatsRate) {
        if (System.currentTimeMillis() >= nextUpdateStatsTime){
            nextUpdateStatsTime = (long) (System.currentTimeMillis() + MILLIS_PER_SECOND * updateStatsRate);
            log.info("FPS: " + (fps * USPS) + " UPS: " + (ups * USPS));
            fps = 0;
            ups = 0;
        }
    }

    private void stopGame() {
    }

}
