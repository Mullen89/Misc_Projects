package main;

import display.Display;

public class Game implements Runnable {

    private Thread thread;
    private Display display;
    private boolean running;

    private String title;
    private int height;
    private int width;

    public Game(String title, int height, int width) {

        this.title = title;
        this.height = height;
        this.width = width;
    }

    private void tick() {

    }

    private void render() {

    }

    private void init() {
        display = new Display(title, height, width);
    }

    @Override
    public void run() {
        init();

        while (running) {
            tick();
            render();
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
