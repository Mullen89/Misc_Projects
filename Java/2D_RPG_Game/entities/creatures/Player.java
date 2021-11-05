package entities.creatures;

import java.awt.Graphics;

import entities.Creature;
import graphics.Assets;
import graphics.ImageLoader;
import main.Game;

public class Player extends Creature {

    private Game game;

    public Player(Game game, float x, float y) {
        super(x, y, Creature.DEFAULT_CREATURE_HEIGHT, Creature.DEFAULT_CREATURE_HEIGHT);
        this.game = game;
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;

        if (game.getKeyManager().up) {
            yMove = -speed;
            System.out.println("UP");
        }
        if (game.getKeyManager().down) {
            yMove = speed;
            System.out.println("DOWN");
        }
        if (game.getKeyManager().left) {
            xMove = -speed;
            System.out.println("LEFT");
        }
        if (game.getKeyManager().right) {
            xMove = speed;
            System.out.println("RIGHT");
        }

    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, height, width, null);
    }

}
