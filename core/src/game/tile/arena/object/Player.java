package game.tile.arena.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;
import game.tile.arena.util.SpriteSheet;

public class Player extends GameObject {

    public Player(Position p) {
        super(p, SpriteSheet.PLAYER_DOWN.getTexture());
        sprites.add(SpriteSheet.PLAYER_DOWN);
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprites.get(0).getAnim(166).getKeyFrame(1, true), pos.x, pos.y);
    }
}
