package main;

import display.Display;
import graphics.Assets;
import graphics.ImageLoader;
import graphics.SpriteSheet;
import states.GameState;
import states.StateManager;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.Thread.State;
import java.awt.Graphics;

public class Game implements Runnable {

    private Thread thread;
    private Display display;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;

    // States
    private StateManager gameState;

    private String title;
    private int height;
    private int width;

    public Game(String title, int height, int width) {
        this.title = title;
        this.height = height;
        this.width = width;
    }

    private void tick() {
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // Draws out the terrain
        // for (int i = 0; i < display.getWidth(); i += 35) {
        // for (int j = 0; j < display.getHeight(); j += 35) {
        // g.drawImage(Assets.grass, i, j, null);
        // }
        // }

        // Draws player
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }

        bs.show();
        g.dispose();
    }

    private void clear(Graphics g) {
        g.clearRect(0, 0, width, height);
    }

    private void init() {
        display = new Display(title, height, width);
        Assets.init();

        gameState = new GameState();
        StateManager.setState(gameState);
    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            ;
            delta += (now - lastTime) / timePerTick;

            if (delta >= 1) {
                tick();
                render();
            }
        }
        stop();
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            running = false;
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
