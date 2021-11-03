package main;

import display.Display;
import graphics.ImageLoader;
import graphics.SpriteSheet;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Game implements Runnable {

    private Thread thread;
    private Display display;
    private boolean running;
    private BufferStrategy bs;
    private Graphics g;
    private String imagesFilePath = "../res/textures/";

    private BufferedImage masterImage;
    private BufferedImage sprite1;
    private SpriteSheet sheet;

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
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        // clear(g);
        g = bs.getDrawGraphics();
        g.drawImage(sprite1, 400, 300, null);

        bs.show();
        g.dispose();
    }

    private void clear(Graphics g) {
        g.clearRect(0, 0, width, height);
    }

    private void init() {
        display = new Display(title, height, width);
        masterImage = ImageLoader.loadImage(imagesFilePath + "sprite_sheet_1-2.png");
        sheet = new SpriteSheet(masterImage);
        sprite1 = sheet.cropSheet(2, 1);
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
