package game.tile.arena.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.tile.arena.util.InputManager;
import game.tile.arena.util.Joystick;
import game.tile.arena.util.Position;

public class Player extends Entity {

    public Player() {
        super("player");
    }

    @Override
    public void update(int delta) {
        updatePosition(Joystick.getPosition(Joystick.MOVEMENT).scale(1f/delta));
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
    }
}
