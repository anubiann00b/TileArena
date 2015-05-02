package game.tile.arena.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class EntitySprite {

    private Position scale;

    private Animation[] animations;
    private ImageMask[][] masks;
    private int animSpeed;
    private int directionFacing;
    private int count;
    private int currentFrame = 0;

    public EntitySprite(String filePrefix, int fps, int size) {
        scale = new Position(size, size);
        animations = new Animation[4];
        masks = new ImageMask[4][];
        animSpeed = fps;
        directionFacing = 0;
        this.loadAnim(filePrefix + "_right.png", 0);
        this.loadAnim(filePrefix + "_up.png", 1);
        this.loadAnim(filePrefix + "_left.png", 2);
        this.loadAnim(filePrefix + "_down.png", 3);
    }

    public void render(SpriteBatch batch, int delta, Position p) {
        count += delta;
        currentFrame = animations[directionFacing].getKeyFrameIndex(count);
        batch.draw(animations[directionFacing].getKeyFrames()[currentFrame], p.x - scale.x / 2, p.y - scale.y / 2, scale.x, scale.y);
    }

    public void setDirection(int dir) {
        directionFacing = dir;
    }

    private void loadAnim(String file, int dir) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(file));
        Texture texture = new Texture(pixmap);
        int width = texture.getWidth();
        int height = texture.getHeight();

        TextureRegion[] regions = TextureRegion.split(texture, width/(width/height), height)[0];
        animations[dir] = new Animation(animSpeed, regions);
        animations[dir].setPlayMode(Animation.PlayMode.LOOP);

        masks[dir] = new ImageMask[regions.length];
        for (int i=0;i<regions.length;i++)
            masks[dir][i] = new ImageMask(regions[i], pixmap);
    }

    public void notMoving() {
        count = animSpeed;
    }

    public ImageMask getMask() {
        return masks[directionFacing][currentFrame];
    }

    public boolean isCollision(Projectile p, Position pos) {
        return getMask().intersects(p, pos);
    }

    public boolean isCollision(Entity e, Position pos) {
        return getMask().intersects(e.sprite.getMask(), pos, e.pos);
    }
}
