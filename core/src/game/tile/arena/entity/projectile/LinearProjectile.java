package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;

public class LinearProjectile extends Projectile {

    public static class Builder  {

        private String filePrefix;
        private Position pos;
        private Position movement;
        private boolean orientation;
        private int hits = 1;
        private int damage = 1;

        public Builder(String filePrefix, Position pos, Position movement, boolean orientation) {
            this.filePrefix = filePrefix;
            this.pos = pos;
            this.movement = movement;
            this.orientation = orientation;
        }

        public Builder setHits(int hits) {
            this.hits = hits;
            return this;
        }

        public Builder setDamage(int damage) {
            this.damage = damage;
            return this;
        }

        public LinearProjectile create() {
            return new LinearProjectile(filePrefix, pos, movement, orientation, hits, damage);
        }
    }

    // Direction and speed.
    private Position movement;

    public LinearProjectile(String filePrefix, Position pos, Position movement, boolean orientation, int hits, int damage) {
        super(filePrefix, pos, orientation, hits, damage);
        this.movement = movement;
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
