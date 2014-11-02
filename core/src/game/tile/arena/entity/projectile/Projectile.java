package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import game.tile.arena.Game;
import game.tile.arena.entity.Entity;
import game.tile.arena.sprite.Image;
import game.tile.arena.util.Position;

public abstract class Projectile extends Object {

    private static AtomicInteger idCounter = new AtomicInteger();
    public final int id;
    private List<Entity> alreadyHit;

    public Image sprite;

    public Position pos;
    public int hits;
    public final boolean orientation;
    public final int damage;

    public Projectile(String filePrefix, Position pos, boolean orientation, int hits, int damage) {
        id = idCounter.getAndIncrement();
        alreadyHit = new ArrayList<Entity>();
        sprite = new Image(filePrefix);
        this.orientation = orientation;
        this.pos = pos;
        this.hits = hits;
        this.damage = damage;
    }

    public abstract boolean update(double delta);

    public abstract void render(SpriteBatch batch);

    public void draw(SpriteBatch batch, float dir) {
        draw(batch, 4, dir);
    }

    public void draw(SpriteBatch batch, float scale, float dir) {
        if (pos.inView(64))
            sprite.render(batch, pos, scale, dir);
    }

    protected boolean checkCollision() {
        for (Entity e : Game.objects) {
            if (e.orientation != orientation) {
                if (e.isCollision(this)) {
                    if (alreadyHit.contains(e))
                        continue;
                    e.hit();
                    alreadyHit.add(e);
                    hits--;
                    if (hits <= 0)
                        return true;
                }
            }
        }
        return false;
    }

    protected boolean valid() {
        return pos.inWorld(64);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Projectile))
            return false;
        return ((Projectile)o).id == id;
    }
}
