package game.tile.arena.entity.attack;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.LinearProjectile;
import game.tile.arena.util.Position;

public class AttackBow implements Attack {

    private int timer;
    private int shotCounter;
    private final int FIRE_TIME;
    private final int RELOAD_TIME;
    private final int SHOTS;

    public AttackBow(int reload) {
        this(reload, reload, 1);
    }

    public AttackBow(int fire, int reload, int shots) {
        FIRE_TIME = fire;
        RELOAD_TIME = reload;
        SHOTS = shots;
        shotCounter = 0;
        timer = FIRE_TIME;
    }

    @Override
    public void update(int delta) {
        Position attackStick = Game.joysticks.getPosition(Game.joysticks.ATTACK);
        if (timer<0 && !attackStick.isZero()) {
            Game.projectiles.add(new LinearProjectile("arrow", Game.player.pos, new Position(attackStick.getDir(), 8)));
            if (shotCounter <= 0) {
                timer = RELOAD_TIME;
                shotCounter = SHOTS;
            } else {
                timer = FIRE_TIME;
                shotCounter--;
            }
        }
        timer -= delta;
    }
}
