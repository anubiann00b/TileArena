package game.tile.arena.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import game.tile.arena.util.Position;

public class Image {

    Texture texture;

    public Image(String file) {
        texture = new Texture(file + ".png");
    }

    public void renderNoCenter(SpriteBatch batch, Position pos, float scale) {
        batch.draw(texture, pos.x, pos.y, scale*texture.getWidth(), scale*texture.getHeight());
    }

    public void render(SpriteBatch batch, Position pos) {
        batch.draw(texture, pos.x-texture.getWidth()/2, pos.y-texture.getHeight()/2);
    }

    public void renderNoCamera(SpriteBatch batch, Position pos, float scale) {
        batch.draw(texture, pos.x-texture.getWidth()/2*scale, pos.y-texture.getHeight()/2*scale,
                scale*texture.getWidth(), scale*texture.getHeight());
    }

    public void render(SpriteBatch batch, Position pos, float scale) {
        batch.draw(texture, pos.x-texture.getWidth()/2*scale, pos.y-texture.getHeight()/2*scale,
                scale*texture.getWidth(), scale*texture.getHeight());
    }

    public void render(SpriteBatch batch, Position pos, Position scale) {
        batch.draw(texture, pos.x-texture.getWidth()/2, pos.y-texture.getHeight()/2, scale.x, scale.y);
    }

    public void render(SpriteBatch batch, Position pos, float scale, float rotation) {
        batch.draw(texture, pos.x-texture.getWidth()/2, pos.y-texture.getHeight()/2,
                texture.getWidth()/2, texture.getHeight()/2, texture.getWidth(), texture.getHeight(),
                scale, scale, (float)(rotation*180/Math.PI),
                0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public int getWidth() {
        return texture.getWidth();
    }
}
