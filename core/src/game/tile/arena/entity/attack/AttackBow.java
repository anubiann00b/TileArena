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
    }

    @Override
    public void equip() {
        shotCounter = 0;
        timer = FIRE_TIME;
    }

    @Override
    public void dequip() {
        shotCounter = 0;
        timer = FIRE_TIME;
    }

    @Override
    public void update(double delta, Position target, boolean orientation) {
        if (timer<0 && !target.isZero()) {
            Game.projectiles.add(new LinearProjectile("arrow", Game.player.pos, new Position(target.getDir(), 8), orientation, 2));
            if (shotCounter <= 0) {
                timer = RELOAD_TIME;
                shotCounter = SHOTS;
            } else {
                timer = FIRE_TIME;
                shotCounter--;
            }
        }
        timer -= delta*Game.FPS;
    }
}
