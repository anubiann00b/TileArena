package game.tile.arena.util.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import game.tile.arena.util.Position;
import game.tile.arena.util.controller.Joystick;

public class JoystickInput implements InputProcessor {

    private final Joystick joystick;

    public JoystickInput(Joystick j) {
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
        return joystick.onDown(new Position(screenX, Gdx.graphics.getHeight() - screenY), pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return joystick.onUp(new Position(screenX, Gdx.graphics.getHeight() - screenY), pointer);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return joystick.onDrag(new Position(screenX, Gdx.graphics.getHeight() - screenY), pointer);
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
