package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;

public class LinearProjectile extends Projectile {

    // Direction and speed.
    private Position movement;

    public LinearProjectile(String filePrefix, Position p, Position m, boolean o) {
        super(filePrefix, p, o);
        movement = m.scaleY(1.0/16.0);
    }

    @Override
    public boolean update(int delta) {
        pos = pos.addPolar(movement.scaleY(delta));
        return pos.inWorld(64);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.draw(batch, movement.x);
    }
}
