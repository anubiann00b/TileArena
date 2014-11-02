package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import game.tile.arena.Game;
import game.tile.arena.entity.Entity;
import game.tile.arena.sprite.Image;
import game.tile.arena.util.Position;

public abstract class Projectile extends Object {

    private static int idCounter;
    public final int id;

    private List<Entity> alreadyHit;

    public Image sprite;
    public Position pos;
    public int hits;
    public final boolean orientation;

    public Projectile(String filePrefix, Position p, boolean o, int h) {
        id = idCounter++;
        alreadyHit = new ArrayList<Entity>();
        sprite = new Image(filePrefix);
        orientation = o;
        pos = p;
        hits = h;
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
