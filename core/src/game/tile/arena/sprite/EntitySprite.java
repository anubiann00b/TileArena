package game.tile.arena.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class EntitySprite {

    private Position scale;

    private Animation[] animations;
    private int animSpeed;
    private int directionFacing;
    private int count;

    public EntitySprite(String filePrefix, int fps, int size) {
        scale = new Position(size, size);
        animations = new Animation[4];
        animSpeed = fps;
        directionFacing = 0;
        this.loadAnim(filePrefix + "_right.png", 0);
        this.loadAnim(filePrefix + "_up.png", 1);
        this.loadAnim(filePrefix + "_left.png", 2);
        this.loadAnim(filePrefix + "_down.png", 3);
    }

    public void render(SpriteBatch batch, int delta, Position p) {
        count += delta;
        batch.draw(animations[directionFacing].getKeyFrame(count, true), p.x-scale.x/2, p.y-scale.y/2, scale.x, scale.y);
    }

    public void setDirection(int dir) {
        directionFacing = dir;
    }

    private void loadAnim(String file, int dir) {
        Texture texture = new Texture(file);
        int width = texture.getWidth();
        int height = texture.getHeight();
        TextureRegion[] regions = TextureRegion.split(texture, width/(width/height), height)[0];
        animations[dir] = new Animation(animSpeed, regions);
    }

    public void notMoving() {
        count = animSpeed;
    }

    public boolean isCollision(Projectile p, Position pos) {
        return p.pos.x>pos.x && p.pos.y>pos.y && p.pos.x<pos.x+scale.x && p.pos.y<pos.y+scale.y;
    }
}
