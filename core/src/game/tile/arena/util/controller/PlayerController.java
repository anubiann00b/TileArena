package game.tile.arena.util.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;
import game.tile.arena.util.Preferences;

public abstract class PlayerController {

    private static PlayerController playerController;

    public static PlayerController getInstance() {
        if (playerController == null) {
            if (Preferences.get().TOUCH_CONTROLS)
                playerController = new TouchController();
            else
                playerController = new KeyboardController();
            return playerController;
        }
        return null;
    }

    public abstract Position getMovement();
    public abstract Position getAttack();

    public void render(SpriteBatch batch, double delta) {

    }
}
