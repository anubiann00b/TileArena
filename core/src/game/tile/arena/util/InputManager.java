package game.tile.arena.util;

import com.badlogic.gdx.Gdx;

public class InputManager {

    public static boolean isPressed() {
        return Gdx.input.isTouched();
    }

    public static Position getCursor() {
        return new Position(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
    }
}
