package states;

import java.awt.Graphics;

import main.Game;

public abstract class StateManager {

    private static StateManager curState = null;
    protected Game game;

    public static void setState(StateManager state) {
        curState = state;
    }

    public static StateManager getCurrentState() {
        return curState;
    }

    public StateManager(Game game) {
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
