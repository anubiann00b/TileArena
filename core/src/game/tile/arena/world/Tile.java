package game.tile.arena.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.sprite.Image;
import game.tile.arena.util.Position;

public enum Tile {
    GRASS("grass");

    Image sprite;

    Tile(String file) {
        sprite = new Image(file);
    }

    public void render(SpriteBatch batch, Position pos) {
        sprite.renderNoCenter(batch, pos, 4);
    }
}
