package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.LinearProjectile;
import game.tile.arena.util.Position;

public class Player extends Entity {

    public Player(Position p) {
        super("player", p);
    }

    @Override
    public void update(int delta) {
        updatePosition(Game.joysticks.getPosition(Game.joysticks.MOVEMENT).scale(delta*speed));

        Position attack = Game.joysticks.getPosition(Game.joysticks.ATTACK);
        if (!attack.isZero()) {
            Game.projectiles.add(new LinearProjectile("arrow", pos, new Position(attack.getDir(), 1f)));
        }
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
    }
}
