package game.tile.arena.entity.enemy;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.enemy.ai.EnemyAI;
import game.tile.arena.util.Position;

public class Enemy extends Entity {

    private EnemyAI ai;

    public Enemy(String filePrefix, Position p, EnemyAI newAi) {
        super(filePrefix, p);
        ai = newAi;
        ai.setEnemy(this);
    }

    @Override
    public void update(int delta) {
        updatePosition(ai.getRelativePosition().scale(delta*speed));
    }
}
