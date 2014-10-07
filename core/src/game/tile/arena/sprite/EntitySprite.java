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
    private int currentFrame;
    private int count;

    public EntitySprite(String filePrefix, int fps) {
        animations = new Animation[4];
        animSpeed = fps;
        loadAnim(filePrefix + "_right.png", 0);
        loadAnim(filePrefix + "_up.png", 0);
        loadAnim(filePrefix + "_left.png", 0);
        loadAnim(filePrefix + "_down.png", 0);
    }

    public void render(SpriteBatch batch, int delta, Position p) {
        render(batch, delta, p, defaultScale);
    }

    public void render(SpriteBatch batch, int delta, Position p, Position s) {
        count += delta;
        batch.draw(animations[currentFrame].getKeyFrame(count, true), p.x, p.y, s.x, s.y);
    }

    private void loadAnim(String file, int dir) {
        Texture texture = new Texture(file);
        TextureRegion[] regions = TextureRegion.split(texture, texture.getWidth()/4, texture.getHeight())[0];
        animations[dir] = new Animation(animSpeed, regions);
    }
}
