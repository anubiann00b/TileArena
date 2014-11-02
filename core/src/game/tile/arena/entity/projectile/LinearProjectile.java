package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;

public class LinearProjectile extends Projectile {

    // Direction and speed.
    private Position movement;

    public LinearProjectile(String filePrefix, Position pos, Position dPos, boolean o, int hits) {
        super(filePrefix, pos, o, hits);
        movement = dPos;
    }

    @Override
    public boolean update(double delta) {
        pos = pos.addPolar(movement.scaleY(delta));
        return valid() && !checkCollision();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.draw(batch, movement.x);
    }
}
