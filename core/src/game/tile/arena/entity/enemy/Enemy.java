package game.tile.arena.entity.enemy;

import com.badlogic.gdx.Gdx;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.Game;
import game.tile.arena.entity.Entity;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.enemy.ai.EnemyAI;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class Enemy extends Entity {

    private EnemyAI ai;

    private Attack currentAttack;
    private int currentAttackIndex = -1;
    private List<Attack> attackList = new LinkedList<Attack>();

    public Enemy(String filePrefix, Position p, EnemyAI newAi) {
        super(filePrefix, p, Game.ENEMY);
        ai = newAi;
        ai.setEnemy(this);
    }

    @Override
    public void update(int delta) {
        updatePosition(ai.getRelativePosition().scale(delta*speed));
        for (Projectile p : Game.projectiles)
            if (p.orientation != orientation)
                if (isCollision(p))
                    hit = true;
    }
}
