package game.tile.arena.entity.projectile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.Image;
import game.tile.arena.util.Position;

public abstract class Projectile {

    public Image sprite;
    public Position pos;

    public Projectile(String filePrefix, Position p) {
        sprite = new Image(filePrefix);
        pos = p;
    }

    public abstract boolean update(int delta);

    public abstract void render(SpriteBatch batch);

    public void draw(SpriteBatch batch, float dir) {
        sprite.render(batch, pos, 4, dir);
    }

    public void draw(SpriteBatch batch, float scale, float dir) {
        sprite.render(batch, pos, scale, dir);
    }
}
