package game.tile.arena.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;

public class Image {

    Texture texture;

    public Image(String file) {
        texture = new Texture(file);
    }

    public void render(SpriteBatch batch, Position pos) {
        batch.draw(texture, pos.x-texture.getWidth()/2, pos.y-texture.getHeight()/2);
    }

    public void render(SpriteBatch batch, Position pos, int scale) {
        batch.draw(texture, pos.x-texture.getWidth()/2*scale, pos.y-texture.getHeight()/2*scale,
                scale*texture.getWidth(), scale*texture.getHeight());
    }

    public void render(SpriteBatch batch, Position pos, Position scale) {
        batch.draw(texture, pos.x-texture.getWidth()/2, pos.y-texture.getHeight()/2, scale.x, scale.y);
    }

    public int getWidth() {
        return texture.getWidth();
    }
}
