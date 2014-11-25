package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.atomic.AtomicInteger;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.sprite.EntitySprite;
import game.tile.arena.util.Position;

public abstract class Entity implements Comparable<Entity> {

    private static final int COLOR_INCREMENTS = 500;
    private static final int COLOR_MAX = COLOR_INCREMENTS*5-1;

    private static AtomicInteger idCounter = new AtomicInteger();
    public final int id;
    public final boolean orientation;

    public Position pos;

    private Position dpos = new Position(0, 0);
    protected EntitySprite sprite;
    protected boolean moving;
    protected int dir = 1;
    protected int lastDir;

    protected float speed = 8f;
    protected boolean hit = false;
    protected int colorCounter = 0;
    protected int health;

    protected Entity(String filePrefix, Position pos, int animSpeed, boolean orientation, int health, int speed) {
        id = idCounter.getAndIncrement();
        sprite = new EntitySprite(filePrefix, animSpeed, 64);
        this.pos = pos;
        this.orientation = orientation;
        this.health = health;
        this.speed = speed;
    }

    public abstract boolean update(double delta);

    public void render(SpriteBatch batch, double delta) {
        if (colorCounter >= 0)
            colorCounter -= delta*Game.FPS;
        sprite.setDirection(dir);
        if (pos.inView(16)) {
            if ((colorCounter / COLOR_INCREMENTS) % 2 == 0)
                batch.setColor(1f, 1f, 1f, 1f);
            else
                batch.setColor(0.5f, 0f, 0f, 1f);
            sprite.render(batch, (int)dpos.magnitude(), pos);
        }
        hit = false;
    }

    public boolean isCollision(Projectile p) {
        return sprite.isCollision(p, pos);
    }

    public void hit(int damage) {
        health -= damage;
        hit = true;
        colorCounter = COLOR_MAX;
    }

    @Override
    public int compareTo(Entity other) {
        return -Float.valueOf(pos.y).compareTo(other.pos.y);
    }

    public void updatePosition(Position dp, double delta) {
        dpos = dp.scale(delta*speed);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Entity))
            return false;
        return ((Entity)o).id == id;
    }
}
