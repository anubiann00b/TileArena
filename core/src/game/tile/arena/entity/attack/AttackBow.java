package game.tile.arena.entity.attack;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.LinearProjectile;
import game.tile.arena.util.Position;

public class AttackBow implements Attack {

    public static class Builder {

        private int fireTime = 600;
        private int reloadTime = 0;
        private int spreadShots = 1;
        private int spreadAngle = 0;
        private int barrageShots = 0;
        private int pierce = 1;
        private int damage = 1;

        public Builder setFireTime(int fireTime) {
            this.fireTime = fireTime;
            return this;
        }

        public Builder setReloadTime(int reloadTime) {
            this.reloadTime = reloadTime;
            return this;
        }

        public Builder setSpreadShots(int spreadShots) {
            this.spreadShots = spreadShots;
            return this;
        }

        public Builder setSpreadAngle(int spreadAngle) {
            this.spreadAngle = spreadAngle;
            return this;
        }

        public Builder setBarrageShots(int barrageShots) {
            this.barrageShots = barrageShots;
            return this;
        }

        public Builder setPierce(int pierce) {
            this.pierce = pierce;
            return this;
        }

        public AttackBow create() {
            if (reloadTime == 0)
                reloadTime = fireTime;
            return new AttackBow(fireTime, reloadTime, spreadShots, barrageShots, spreadAngle, pierce, damage);
        }
    }

    private int timer;
    private int shotCounter;
    private final int fireTime;
    private final int reloadTime;
    private final int barrageShots;
    private final int spreadShots;
    private final double spread;
    private final int pierce;
    private final int damage;

    private AttackBow(int fireTime, int reloadTime, int spreadShots, int barrageShots, int spread, int pierce, int damage) {
        this.fireTime = fireTime;
        this.reloadTime = reloadTime;
        this.spread = spread * Math.PI / 180;
        this.spreadShots = spreadShots;
        this.barrageShots = barrageShots;
        this.pierce = pierce;
        this.damage = damage;
    }

    @Override
    public void equip() {
        shotCounter = 0;
        timer = fireTime;
    }

    @Override
    public void dequip() {
        shotCounter = 0;
        timer = fireTime;
    }

    @Override
    public void update(double delta, Position target, boolean orientation) {
        if (timer<0 && !target.isZero()) {
            double startDir = target.getDir() - spread * spreadShots /2;
            for(int i=0;i< spreadShots;i++) {
                Game.projectiles.add(new LinearProjectile("arrow", Game.player.pos,
                        new Position(startDir + i* spread, 24), orientation, pierce, damage));
            }
            if (shotCounter <= 0) {
                timer = reloadTime;
                shotCounter = barrageShots;
            } else {
                timer = fireTime;
                shotCounter--;
            }
        }
        timer -= delta*Game.FPS;
    }
}
