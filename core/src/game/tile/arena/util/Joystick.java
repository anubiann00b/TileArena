package game.tile.arena.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.Image;

public class Joystick {

    private boolean captured;
    private final int radius;
    private Position center;
    private Position position;
    private Image stick;
    private Image bg;

    public Joystick(Position pos, int size, String stickImg, String bgImg) {
        center = pos;
        position = pos;
        radius = size;
        stick = new Image(stickImg);
        bg = new Image(bgImg);
    }

    public Position getPosition() {
        return center.subtract(position);
    }

    public void update(int delta) {
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

    public void render(SpriteBatch batch, int delta) {
        bg.render(batch, center, radius/bg.getWidth());
        stick.render(batch, position, 4);
    }
}
