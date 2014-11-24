package game.tile.arena.util.input;

import com.badlogic.gdx.InputProcessor;

import game.tile.arena.util.Preferences;
import game.tile.arena.util.controller.KeyboardController;

public class KeyboardMoveInput implements InputProcessor {

    KeyboardController controller;

    public KeyboardMoveInput(KeyboardController control) {
        controller = control;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Preferences.get().RIGHT)
            controller.keyPressed(0);
        else if (keycode == Preferences.get().UP)
            controller.keyPressed(1);
        else if (keycode == Preferences.get().LEFT)
            controller.keyPressed(2);
        else if (keycode == Preferences.get().DOWN)
            controller.keyPressed(3);
        else
            return false;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Preferences.get().RIGHT)
            controller.keyReleased(0);
        else if (keycode == Preferences.get().UP)
            controller.keyReleased(1);
        else if (keycode == Preferences.get().LEFT)
            controller.keyReleased(2);
        else if (keycode == Preferences.get().DOWN)
            controller.keyReleased(3);
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
