package game.tile.arena.entity.enemy.ai;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class EnemyDodgeAI extends EnemyAI {

    @Override
    public Position getRelativePosition() {
        Position current = new Position(0,0);
        double count = 1;
        for (Projectile p : Game.projectiles) {
            if (p.orientation == enemy.orientation)
                continue;
            double projectileDir = enemy.pos.getDirTo(p.pos);
            if (Math.abs(projectileDir - p.getCurrentMovement().x) < 90) {
                current.scale(1/count).subtract(new Position(projectileDir)).scale(1/(count+1));
                count++;
            }
        }
        return current;
    }
}
