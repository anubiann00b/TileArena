package game.tile.arena.util;

import com.badlogic.gdx.Input;

public class Preferences {

    private static Preferences currentPrefs;

    public static Preferences get() {
        if (currentPrefs == null)
            currentPrefs = new Preferences();
        return currentPrefs;
    }

    public boolean TOUCH_CONTROLS = true;

    public int UP = Input.Keys.W;
    public int DOWN = Input.Keys.S;
    public int LEFT = Input.Keys.A;
    public int RIGHT = Input.Keys.D;
    public int WEAPON_SWITCH = Input.Keys.H;

    public void setTouch(boolean newControls) {
        TOUCH_CONTROLS = newControls;
    }
}
