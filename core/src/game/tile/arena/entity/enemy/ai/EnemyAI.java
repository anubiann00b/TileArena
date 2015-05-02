package game.tile.arena.entity.enemy.ai;

import game.tile.arena.entity.enemy.Enemy;
import game.tile.arena.util.Position;

public abstract class EnemyAI {

    protected Enemy enemy = null;

    public void setEnemy(Enemy e) {
        enemy = e;
    }

    public abstract Position getRelativePosition();
    public void attack() { }
}
