package game.tile.arena.util.input;

import com.badlogic.gdx.InputProcessor;

import game.tile.arena.Game;
import game.tile.arena.util.Preferences;

public class WeaponSwitchInput implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        if (Preferences.get().TOUCH_CONTROLS)
            return false;
        if (keycode == Preferences.get().WEAPON_SWITCH) {
            Game.player.switchWeapon();
            return true;
        }
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
        if (Preferences.get().TOUCH_CONTROLS) {
            Game.player.switchWeapon();
            return true;
        }
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
