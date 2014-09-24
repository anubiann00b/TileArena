package game.tile.arena.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.SpriteSheet;

public class Player extends GameObject {

    public Player() {
        super();
        sprites.add(SpriteSheet.PLAYER_DOWN);
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprites.get(0).getImage(), pos.x, pos.y);
    }
}
