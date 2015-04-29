package game.tile.arena.entity.enemy.ai;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class EnemyDodgeAI extends EnemyAI {

    @Override
    public Position getRelativePosition() {
        Position current = new Position(0,0);
        for (Projectile p : Game.projectiles) {
            if (p.orientation == enemy.orientation)
                continue;
            float distance = enemy.pos.getDistance(p.pos);
            double projectileDir = enemy.pos.getDirTo(p.pos);
            if (distance > 150)
                continue;
            current = current.subtract(
                    Position.findOrthogonalVector(p.pos, enemy.pos, p.getCurrentMovement().x)
                            .normalize(0.5f)
                            .scale(1-Math.sqrt(distance/300)) // scale by distance
            );
        }
        if (enemy.pos.getDistance(Game.player.pos) < 300)
            current = current.add(enemy.pos.subtract(Game.player.pos).normalize(0.1f));
        return current;
    }
}
