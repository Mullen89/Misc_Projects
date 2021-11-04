package entities.creatures;

import java.awt.Graphics;

import entities.Creature;
import graphics.Assets;
import graphics.ImageLoader;
import main.Game;

public class Player extends Creature {

    private Game game;

    public Player(Game game, float x, float y) {
        super(x, y);
        this.game = game;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up) {
            y -= 3;
            System.out.println("UP");
        }
        if (game.getKeyManager().down) {
            y += 3;
            System.out.println("DOWN");
        }
        if (game.getKeyManager().left) {
            x -= 3;
            System.out.println("LEFT");
        }
        if (game.getKeyManager().right) {
            x += 3;
            System.out.println("RIGHT");
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, null);
    }

}
