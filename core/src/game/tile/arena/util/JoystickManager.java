package game.tile.arena.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.sprite.Image;
import game.tile.arena.util.input.JoystickInput;

public class JoystickManager {

    private static JoystickManager joystickManager;

    public static JoystickManager getInstance() {
        if (joystickManager == null) {
            joystickManager = new JoystickManager();
            return joystickManager;
        }
        return null;
    }

    private JoystickManager() { }

    public Joystick[] sticks;
    public final int NUM_STICKS = 2;
    public final int MOVEMENT = 0;
    public final int ATTACK = 1;

    public Position getPosition(int stick) {
        return sticks[stick].position.subtract(sticks[stick].center);
    }

    public void init() {
        sticks = new Joystick[NUM_STICKS];
        sticks[MOVEMENT] = new Joystick(new Position(250, 250), 100, "circle.png", "joystick_bg.png");
        sticks[ATTACK] = new Joystick(new Position(Gdx.graphics.getWidth()-250, 250), 100, "circle.png", "joystick_bg.png");
    }

    public void update(int delta) {

    }

    public void render(SpriteBatch batch, int delta) {
        sticks[MOVEMENT].renderStick(batch, delta);
        sticks[ATTACK].renderStick(batch, delta);
    }
}