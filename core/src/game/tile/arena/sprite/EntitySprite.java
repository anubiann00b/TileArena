package game.tile.arena.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.tile.arena.util.Position;

public class EntitySprite {

    private static Position defaultScale = new Position(64, 64);

    private Animation[] animations;
    private int animSpeed;
    private int directionFacing;
    private int count;

    private int width;
    private int height;

    public EntitySprite(String filePrefix, int fps) {
        animations = new Animation[4];
        animSpeed = fps;
        directionFacing = 0;
        this.loadAnim(filePrefix + "_right.png", 0);
        this.loadAnim(filePrefix + "_up.png", 1);
        this.loadAnim(filePrefix + "_left.png", 2);
        this.loadAnim(filePrefix + "_down.png", 3);
    }

    public void render(SpriteBatch batch, int delta, Position p) {
        render(batch, delta, p, defaultScale);
    }

    public void render(SpriteBatch batch, int delta, Position p, Position s) {
        count += delta;
        batch.draw(animations[directionFacing].getKeyFrame(count, true), p.x-s.x/2, p.y-s.y/2, s.x, s.y);
    }

    public void setDirection(int dir) {
        directionFacing = dir;
    }

    private void loadAnim(String file, int dir) {
        Texture texture = new Texture(file);
        TextureRegion[] regions = TextureRegion.split(texture, texture.getWidth()/4, texture.getHeight())[0];

        width = regions[0].getRegionWidth();
        height = regions[0].getRegionHeight();

        animations[dir] = new Animation(animSpeed, regions);
    }
}
