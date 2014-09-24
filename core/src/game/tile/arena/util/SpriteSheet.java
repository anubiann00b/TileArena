package game.tile.arena.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents an array of images from a single texture.
 */
public class SpriteSheet {

    private Texture texture;
    private TextureRegion[] images;

    public static final SpriteSheet SHIP_VARIANT_1 = new SpriteSheet("ship_Sprite1.png",23,32);

    public SpriteSheet(String path, int width, int height) {
        texture = new Texture(path);
        TextureRegion[][] regions = TextureRegion.split(texture, width, height);
        images = new TextureRegion[regions.length * regions[0].length];

        for(int i = 0; i < regions.length; i++)
            System.arraycopy(regions[i], 0, images, i * regions[i].length, regions[i].length);
    }

    public Animation getAnim(float frameSpeed) {
        return new Animation(frameSpeed, images);
    }
}
