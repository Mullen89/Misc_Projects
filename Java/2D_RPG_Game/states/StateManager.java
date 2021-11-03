package states;

import java.awt.Graphics;

public abstract class StateManager {

    private static StateManager curState = null;

    public static void setState(StateManager state) {
        curState = state;
    }

    public static StateManager getCurrentState() {
        return curState;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
