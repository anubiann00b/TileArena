package game.tile.arena.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.tile.arena.util.InputManager;
import game.tile.arena.util.Position;

public class Player extends Entity {

    private boolean movementStickCaptured;
    private Position movementStickCenter;
    private Position movementStickCurrent;

    public Player() {
        super("player");
        movementStickCenter = new Position(300, 300);
        movementStickCurrent = new Position(300, 300);
    }

    @Override
    public void update(int delta) {
        if (InputManager.isPressed()) {
            if (movementStickCenter.inRange(100, InputManager.getCursor())) {
                movementStickCaptured = true;
                movementStickCurrent = InputManager.getCursor();
            }
        } else if (InputManager.isHeld()) {
            if (movementStickCaptured) {
                movementStickCurrent = movementStickCenter.limit(100, InputManager.getCursor());
            }
        } else if (InputManager.isReleased()) {
            movementStickCaptured = false;
            movementStickCurrent = movementStickCenter;
        }
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
        sprite.render(batch, delta, movementStickCurrent);
    }
}
