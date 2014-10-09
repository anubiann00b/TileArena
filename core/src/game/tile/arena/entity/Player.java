package game.tile.arena.entity;

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
        movementStickCenter = new Position(100, 100);
        movementStickCurrent = new Position(100, 100);
    }

    @Override
    public void update(int delta) {
        if (InputManager.isPressed()) {
            if (movementStickCenter.inRange(50, InputManager.getCursor())) {
                movementStickCaptured = true;
                movementStickCurrent = InputManager.getCursor();
            }
        } else if (InputManager.isHeld()) {
            if (movementStickCaptured) {
                movementStickCenter = movementStickCenter.limit(50, InputManager.getCursor());
            }
        }
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
        sprite.render(batch, delta, movementStickCurrent);
    }
}
