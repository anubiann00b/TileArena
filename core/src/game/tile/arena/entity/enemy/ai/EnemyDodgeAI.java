package game.tile.arena.entity.enemy.ai;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class EnemyDodgeAI extends EnemyAI {

    @Override
    public Position getRelativePosition() {
        Position current = new Position();
        for (Projectile p : Game.projectiles) {
            if (p.orientation == enemy.orientation)
                continue;
            float distance = enemy.pos.getDistance(p.pos);
            if (distance > 150)
                continue;
            Position ortho = Position.findOrthogonalVector(p.pos, enemy.pos, p.getCurrentMovement().x);
            double orthoDistance = ortho.magnitude();
            current = current.subtract(ortho.normalize(1f).scale(1-Math.sqrt(orthoDistance/150)));
        }
        return current;
    }
}
