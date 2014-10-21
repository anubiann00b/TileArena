package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.Image;
import game.tile.arena.util.Position;

public abstract class Projectile {

    public Image sprite;
    public Position pos;
    public final boolean orientation;

    public Projectile(String filePrefix, Position p, boolean o) {
        sprite = new Image(filePrefix);
        orientation = o;
        pos = p;
    }

    public abstract boolean update(int delta);

    public abstract void render(SpriteBatch batch);

    public void draw(SpriteBatch batch, float dir) {
        draw(batch, 4, dir);
    }

    public void draw(SpriteBatch batch, float scale, float dir) {
        if (pos.inView(64))
            sprite.render(batch, pos, scale, dir);
    }
}
