package game.tile.arena.entity.enemy;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.Game;
import game.tile.arena.entity.Entity;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.enemy.ai.EnemyAI;
import game.tile.arena.util.Position;

public class Enemy extends Entity {

    public static class Builder {

        private String filePrefix;
        private Position pos;
        private int animSpeed = 166;
        private int health = 10;
        private int speed = 8;
        private EnemyAI ai;

        public Builder(String filePrefix, Position pos, EnemyAI ai) {
            this.filePrefix = filePrefix;
            this.pos = pos;
            this.ai = ai;
        }

        public Builder setFilePrefix(String filePrefix) {
            this.filePrefix = filePrefix;
            return this;
        }

        public Builder setPos(Position pos) {
            this.pos = pos;
            return this;
        }

        public Builder setAnimSpeed(int animSpeed) {
            this.animSpeed = animSpeed;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public Enemy createEnemy() {
            return new Enemy(filePrefix, pos, animSpeed, health, speed, ai);
        }
    }

    private EnemyAI ai;

    private Attack currentAttack;
    private int currentAttackIndex = -1;
    private List<Attack> attackList = new LinkedList<Attack>();

    public Enemy(String filePrefix, Position pos, int animSpeed, int health, int speed, EnemyAI ai) {
        super(filePrefix, pos, animSpeed, Game.ENEMY, health, speed);
        this.ai = ai;
        this.ai.setEnemy(this);
    }

    @Override
    public boolean update(double delta) {
        updatePosition(ai.getRelativePosition(), delta);
        return health > 0;
    }
}
