package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;

public class Player extends Entity {

    public Player() {
        super("player");
    }

    @Override
    public void update(int delta) {
        updatePosition(Game.joysticks.getPosition(Game.joysticks.MOVEMENT).scale(1f/delta));
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
    }
}
