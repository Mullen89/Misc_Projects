package main;

import display.Display;
import graphics.Assets;
import graphics.ImageLoader;
import graphics.SpriteSheet;
import input.KeyManager;
import states.GameState;
import states.MainMenuState;
import states.SettingsMenuState;
import states.StateManager;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.Thread.State;
import java.awt.Graphics;

public class Game implements Runnable {

    private Thread thread;
    private Display display;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;

    // States
    private StateManager gameState;
    private StateManager menuState;
    private StateManager settingsState;

    // Input
    private KeyManager keyManager;

    private String title;
    private int height;
    private int width;

    public Game(String title, int height, int width) {
        this.title = title;
        this.height = height;
        this.width = width;
        keyManager = new KeyManager();
    }

    private void tick() {
        keyManager.tick();

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
        clear(g);

        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }

        bs.show();
        g.dispose();
    }

    private void init() {
        display = new Display(title, height, width);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MainMenuState(this);
        settingsState = new SettingsMenuState(this);
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
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                delta--;
                ticks++;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
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

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void clear(Graphics g) {
        g.clearRect(0, 0, width, height);
    }
}
