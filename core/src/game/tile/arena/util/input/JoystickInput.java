package game.tile.arena.util.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import game.tile.arena.util.KeyMap;
import game.tile.arena.util.Position;
import game.tile.arena.util.joystick.Joystick;

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
        if (keycode == KeyMap.RIGHT)
            joystick.keyPressed(0);
        else if (keycode == KeyMap.UP)
            joystick.keyPressed(1);
        else if (keycode == KeyMap.LEFT)
            joystick.keyPressed(2);
        else if (keycode == KeyMap.DOWN)
            joystick.keyPressed(3);
        else
            return false;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == KeyMap.RIGHT)
            joystick.keyReleased(0);
        else if (keycode == KeyMap.UP)
            joystick.keyReleased(1);
        else if (keycode == KeyMap.LEFT)
            joystick.keyReleased(2);
        else if (keycode == KeyMap.DOWN)
            joystick.keyReleased(3);
        else
            return false;
        return true;
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
