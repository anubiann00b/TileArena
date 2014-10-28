package game.tile.arena.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.sprite.EntitySprite;
import game.tile.arena.util.Position;

public abstract class Entity implements Comparable<Entity> {

    private static int idCounter;
    public final int id;

    public Position pos;
    private Position dpos = new Position(0, 0);

    protected EntitySprite sprite;
    protected boolean moving;
    protected int dir;
    protected int lastDir;
    protected float speed;

    protected boolean hit = false;
    protected int colorCounter = 0;
    private final int COLOR_INCREMENTS = 500;
    private final int COLOR_MAX = COLOR_INCREMENTS*5-1;

    public final boolean orientation;

    public Entity(String filePrefix, Position p, boolean o) {
        this(filePrefix, p, 80, o);
    }

    public Entity(String filePrefix, Position p, int animSpeed, boolean o) {
        id = idCounter++;
        sprite = new EntitySprite(filePrefix, animSpeed, 64);
        pos = p;
        dir = 1;
        speed = 8f;
        orientation = o;
    }

    public abstract void update(double delta);

    public void render(SpriteBatch batch, double delta) {
        sprite.setDirection(dir);
        if (pos.inView(16)) {
            batch.setColor(1f, 1f, 1f, 1f);
            if (colorCounter >= 0) {
                if ((colorCounter / COLOR_INCREMENTS) % 2 == 0)
                    batch.setColor(0.5f, 0f, 0f, 1f);
                colorCounter -= delta*Game.FPS;
            }
            sprite.render(batch, (int)dpos.magnitude(), pos);
        }
        hit = false;
    }

    public boolean isCollision(Projectile p) {
        return sprite.isCollision(p, pos);
    }

    public void hit() {
        hit = true;
        colorCounter = COLOR_MAX;
    }

    @Override
    public int compareTo(Entity other) {
        return Float.valueOf(pos.y).compareTo(other.pos.y);
        //return hit == other.hit ? 0 : (hit?1:-1);
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
