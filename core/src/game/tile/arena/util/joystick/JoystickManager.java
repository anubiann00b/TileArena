package game.tile.arena.util.joystick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;

public class JoystickManager {

    private static JoystickManager joystickManager;

    public static JoystickManager getInstance() {
        if (joystickManager == null) {
            joystickManager = new JoystickManager();
            return joystickManager;
        }
        return null;
    }

    private JoystickManager() {
        sticks = new Joystick[NUM_STICKS];
        sticks[MOVEMENT] = new Joystick(new Position(250, 250), 100, "circle", "joystick_bg");
        sticks[ATTACK] = new Joystick(new Position(Gdx.graphics.getWidth()-250, 250), 100, "circle", "joystick_bg");
    }

    public Joystick[] sticks;
    public final int NUM_STICKS = 2;
    public final int MOVEMENT = 0;
    public final int ATTACK = 1;

    public Position getPosition(int stick) {
        return sticks[stick].position.subtract(sticks[stick].center).scale(1f/sticks[stick].radius);
    }

    public void render(SpriteBatch batch, double delta) {
        sticks[MOVEMENT].renderStick(batch, delta);
        sticks[ATTACK].renderStick(batch, delta);
    }
}