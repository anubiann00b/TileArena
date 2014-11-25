package game.tile.arena.util.controller;

import com.badlogic.gdx.InputProcessor;

public class MouseAttackInput implements InputProcessor {

    KeyboardController controller;

    public MouseAttackInput(KeyboardController control) {
        controller = control;
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
        return controller.mouseClick(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return controller.mouseUp(screenX, screenY, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return controller.mouseClick(screenX, screenY, pointer);
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
