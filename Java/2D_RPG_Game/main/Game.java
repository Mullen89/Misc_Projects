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
    private BufferedImage terrainImage;
    private BufferedImage sprite1;
    private BufferedImage grassSprite;
    private SpriteSheet sheet;
    private SpriteSheet terrain;

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
        g = bs.getDrawGraphics();
        for (int i = 0; i < display.getWidth(); i += 35) {
            for (int j = 0; j < display.getHeight(); j += 35) {
                g.drawImage(grassSprite, i, j, null);
            }
        }
        g.drawImage(sprite1, 400, 300, null);

        bs.show();
        g.dispose();
    }

    private void clear(Graphics g) {
        g.clearRect(0, 0, width, height);
    }

    private void init() {
        display = new Display(title, height, width);
        masterImage = ImageLoader.loadImage(imagesFilePath + "sprite_sheet_1.png");
        terrainImage = ImageLoader.loadImage(imagesFilePath + "terrain_sheet_1.png");
        sheet = new SpriteSheet(masterImage);
        terrain = new SpriteSheet(terrainImage);
        sprite1 = sheet.cropSheet(2, 1);
        grassSprite = terrain.cropSheet(0, 0);

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
