package game.tile.arena.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.tile.arena.util.InputManager;
import game.tile.arena.util.Joystick;
import game.tile.arena.util.Position;

public class Player extends Entity {

    private Joystick movementStick;


    public Player() {
        super("player");
        movementStick = new Joystick(new Position(300, 300), 100, "circle.png", "joystick_bg.png");
    }

    @Override
    public void update(int delta) {
        movementStick.update(delta);
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
        movementStick.render(batch, delta);
    }
}
