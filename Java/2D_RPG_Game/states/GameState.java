package states;

import java.awt.Graphics;
import entities.creatures.Player;
import main.Game;

public class GameState extends StateManager {

    private Player player;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 500, 200);
    }

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }

}
