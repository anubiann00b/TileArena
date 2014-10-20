package game.tile.arena.util.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import game.tile.arena.util.Joystick;
import game.tile.arena.util.Position;

public class JoystickInput implements InputProcessor {

    private final Position center;
    private final int radius;
    private final Joystick joystick;

    public JoystickInput(Position pos, int size, Joystick j) {
        center = pos;
        radius = size;
        joystick = j;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return joystick.onDown(new Position(screenX, Gdx.graphics.getHeight()-screenY), pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return joystick.onUp(new Position(screenX, Gdx.graphics.getHeight()-screenY), pointer);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return joystick.onDrag(new Position(screenX, Gdx.graphics.getHeight()-screenY), pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
