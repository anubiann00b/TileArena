package game.tile.arena.entity;

import com.badlogic.gdx.Gdx;

import game.tile.arena.util.InputManager;

public class Player extends Entity {

    public Player() {
        super("player");
    }

    @Override
    public void update(int delta) {
        if (InputManager.isPressed()) {
            pos = InputManager.getCursor();
        }
    }
}
