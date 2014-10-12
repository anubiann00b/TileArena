package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.EntitySprite;
import game.tile.arena.util.Position;

public abstract class Entity implements Comparable<Entity> {

    protected EntitySprite sprite;
    public Position pos;

    public Entity(String filePrefix) {
        this(filePrefix, 166);
    }

    public Entity(String filePrefix, int animSpeed) {
        sprite = new EntitySprite(filePrefix, animSpeed);
        pos = new Position(0, 0);
    }

    public abstract void update(int delta);

    public void render(SpriteBatch batch, int delta) {
        sprite.render(batch, delta, pos);
    }

    @Override
    public int compareTo(Entity other) {
        return new Float(pos.y).compareTo(other.pos.y);
    }
}
