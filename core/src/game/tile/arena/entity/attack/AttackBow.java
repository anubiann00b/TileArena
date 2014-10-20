package game.tile.arena.entity.attack;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.LinearProjectile;
import game.tile.arena.util.Position;

public class AttackBow implements Attack {

    private int timer;
    private final int RELOAD_TIME;

    public AttackBow(int reload) {
        RELOAD_TIME = reload;
        timer = RELOAD_TIME;
    }

    @Override
    public void update(int delta) {
        Position attackStick = Game.joysticks.getPosition(Game.joysticks.ATTACK);
        if (timer<0 && !attackStick.isZero()) {
            Game.projectiles.add(new LinearProjectile("arrow", Game.player.pos, new Position(attackStick.getDir(), 8)));
            timer = RELOAD_TIME;
        }
        timer -= delta;
    }
}
