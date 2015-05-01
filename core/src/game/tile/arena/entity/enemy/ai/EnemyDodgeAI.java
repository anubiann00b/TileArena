package game.tile.arena.entity.enemy.ai;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class EnemyDodgeAI extends EnemyAI {

    Position currentMovement = new Position(2*Math.PI*Math.random());
    int time = 0;
    int totalTime = 0;

    @Override
    public Position getRelativePosition() {
        Position current = new Position();
        int count = 0;
        for (Projectile p : Game.projectiles) {
            if (p.orientation == enemy.orientation)
                continue;
            float distance = enemy.pos.getDistance(p.pos);
            if (distance > 150)
                continue;
            Position ortho = Position.findOrthogonalVector(p.pos, enemy.pos, p.getCurrentMovement().x);
            double orthoDistance = ortho.magnitude();
            current = current.subtract(ortho.normalize().scale(1-Math.sqrt(orthoDistance/150)));
            count++;
        }
        if (time >= totalTime) {
            time = 0;
            totalTime = (int) (Math.random()*100);
            currentMovement = new Position(2*Math.PI*Math.random());
        }
        if (count == 0) {
            current = current.add(currentMovement).scale(0.25);
        }
        time++;
        return current;
    }
}
