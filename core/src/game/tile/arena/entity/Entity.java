package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.EntitySprite;
import game.tile.arena.util.Position;

public abstract class Entity implements Comparable<Entity> {

    protected EntitySprite sprite;
    public Position pos;
    protected boolean moving;
    protected int dir;
    protected int lastDir;
    protected float speed;

    public Entity(String filePrefix, Position p) {
        this(filePrefix, p, 166);
    }

    public Entity(String filePrefix, Position p, int animSpeed) {
        sprite = new EntitySprite(filePrefix, animSpeed);
        pos = p;
        dir = 1;
        speed = 1f/256f;
    }

    public abstract void update(int delta);

    public void render(SpriteBatch batch, int delta) {
        sprite.setDirection(dir);
        sprite.render(batch, delta, pos);
    }

    @Override
    public int compareTo(Entity other) {
        return new Float(pos.y).compareTo(other.pos.y);
    }

    public void updatePosition(Position dpos) {
        pos = pos.add(dpos);
        moving = true;

        if (dpos.x == 0 && dpos.y == 0) {
            moving = false;
            dir = lastDir;
        } else if (Math.abs(dpos.x) > Math.abs(dpos.y)) {
            if (dpos.x > 0)
                dir = 0;
            else
                dir = 2;
        } else {
            if (dpos.y > 0)
                dir = 1;
            else
                dir = 3;
        }

        lastDir = dir;
    }
}
