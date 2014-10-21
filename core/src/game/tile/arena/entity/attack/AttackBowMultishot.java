package game.tile.arena.entity.attack;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.LinearProjectile;
import game.tile.arena.util.Position;

public class AttackBowMultishot implements Attack {

    private int timer;
    private final int RELOAD_TIME;
    private final int SHOTS;
    private final double SPREAD;

    public AttackBowMultishot(int fire, int shots, double spread) {
        SHOTS = shots;
        SPREAD = spread * Math.PI / 180;
        RELOAD_TIME = fire;
        timer = RELOAD_TIME;
    }

    @Override
    public void update(int delta, Position target) {
        double direction = target.getDir();
        double startDir = direction - SPREAD*SHOTS/2;

        if (timer<0 && !target.isZero()) {
            for(int i=0;i<SHOTS;i++) {
                Game.projectiles.add(new LinearProjectile("arrow", Game.player.pos, new Position(startDir + i*SPREAD, 8)));
            }
            timer = RELOAD_TIME;
        }
        timer -= delta;
    }
}
