package game.tile.arena.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.Image;
import sun.security.util.Length;

public class Joystick {

    private boolean captured;
    private final int radius;
    private Position center;
    private Position position;
    private Image stick;
    private Image bg;

    private Joystick(Position pos, int size, String stickImg, String bgImg) {
        center = pos;
        position = pos;
        radius = size;
        stick = new Image(stickImg);
        bg = new Image(bgImg);
    }

    private void updateStick(int delta) {
        if (InputManager.isPressed()) {
            if (center.inRange(radius, InputManager.getCursor())) {
                captured = true;
                position = InputManager.getCursor();
            }
        } else if (InputManager.isHeld()) {
            if (captured) {
                position = center.limit(radius, InputManager.getCursor());
            }
        } else if (InputManager.isReleased()) {
            captured = false;
            position = center;
        }
    }

    private void renderStick(SpriteBatch batch, int delta) {
        bg.render(batch, center, radius/bg.getWidth());
        stick.render(batch, position, 4);
    }

    public static Position getPosition(int stick) {
        return sticks[stick].position.subtract(sticks[stick].center);
    }

    public static Joystick[] sticks;
    public static final int NUM_STICKS = 2;
    public static final int MOVEMENT = 0;
    public static final int ATTACK = 1;

    public static void init() {
        sticks = new Joystick[NUM_STICKS];
        sticks[MOVEMENT] = new Joystick(new Position(250, 250), 100, "circle.png", "joystick_bg.png");
        sticks[ATTACK] = new Joystick(new Position(Gdx.graphics.getWidth()-250, 250), 100, "circle.png", "joystick_bg.png");
    }

    public static void update(int delta) {
        sticks[MOVEMENT].updateStick(delta);
        sticks[ATTACK].updateStick(delta);
    }

    public static void render(SpriteBatch batch, int delta) {
        sticks[MOVEMENT].renderStick(batch, delta);
        sticks[ATTACK].renderStick(batch, delta);
    }
}
