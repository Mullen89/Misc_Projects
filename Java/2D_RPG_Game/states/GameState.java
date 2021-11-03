package states;

import java.awt.Graphics;
import graphics.Assets;

public class GameState extends StateManager {

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, 200, 100, null);
    }

}
