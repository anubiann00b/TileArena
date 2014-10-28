package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.entity.Entity;
import game.tile.arena.util.Position;

public class LinearProjectile extends Projectile {

    // Direction and speed.
    private Position movement;

    public LinearProjectile(String filePrefix, Position p, Position m, boolean o) {
        super(filePrefix, p, o);
        movement = m;
    }

    @Override
    public boolean update(double delta) {
        pos = pos.addPolar(movement.scaleY(delta));

        for (Entity e : Game.objects) {
            if (e.orientation != orientation) {
                if (e.isCollision(this)) {
                    e.hit();
                    break;
                }
            }
        }

        return pos.inWorld(64);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.draw(batch, movement.x);
    }
}
