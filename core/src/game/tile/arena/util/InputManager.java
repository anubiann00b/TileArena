package game.tile.arena.util;

import com.badlogic.gdx.Gdx;

public class InputManager {

    private static boolean touched;
    private static boolean released;
    private static boolean pressed;

    public static boolean isTouched() { return touched; }
    public static boolean isReleased() { return released; }
    public static boolean isPressed() { return pressed;  }

    public static void update() {
        boolean nTouched = Gdx.input.isTouched();

        pressed = nTouched && !isTouched();
        released = isTouched() && !nTouched;

        touched = nTouched;
    }

    public static Position getCursor() {
        return new Position(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
    }
}
