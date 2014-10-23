package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.sprite.EntitySprite;
import game.tile.arena.util.Position;

public abstract class Entity implements Comparable<Entity> {

    public Position pos;

    private Position dpos = new Position(0, 0);

    protected EntitySprite sprite;
    protected boolean moving;
    protected int dir;
    protected int lastDir;
    protected float speed;

    protected boolean hit = false;

    public final boolean orientation;

    public Entity(String filePrefix, Position p, boolean o) {
        this(filePrefix, p, 166, o);
    }

    public Entity(String filePrefix, Position p, int animSpeed, boolean o) {
        sprite = new EntitySprite(filePrefix, animSpeed, 64);
        pos = p;
        dir = 1;
        speed = 1f/2f;
        orientation = o;
    }

    public abstract void update(int delta);


    public void render(SpriteBatch batch, int delta) {
        sprite.setDirection(dir);
        if (pos.inView(16)) {
            if (hit)
                batch.setColor(0.5f, 0f, 0f, 1f);
            else
                batch.setColor(1f, 1f, 1f, 1f);
            sprite.render(batch, (int) (delta / 2d + dpos.magnitude() / 100d * 16d), pos);
        }
        hit = false;
    }

    public boolean isCollision(Projectile p) {
        return sprite.isCollision(p, pos);
    }

    @Override
    public int compareTo(Entity other) {
        //return Float.valueOf(pos.y).compareTo(other.pos.y);
        return hit == other.hit ? 0 : (hit?1:-1);
    }

    public void updatePosition(Position dp) {
        dpos = dp;
        pos = pos.add(dpos);
        moving = true;

        if (dpos.x == 0 && dpos.y == 0) {
            moving = false;
            dir = lastDir;
            sprite.notMoving();
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
