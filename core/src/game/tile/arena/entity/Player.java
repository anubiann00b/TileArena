package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.Game;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.attack.AttackBow;
import game.tile.arena.util.Position;

public class Player extends Entity {

    private Attack currentAttack;
    private List<Attack> attackList = new LinkedList<Attack>();

    public Player(Position p) {
        super("player", p);
        addAttack(new AttackBow(20, 400, 5));
        addAttack(new AttackBow(400));
    }

    public void addAttack(Attack a) {
        if (currentAttack == null)
            currentAttack = a;
        attackList.add(a);
    }

    @Override
    public void update(int delta) {
        updatePosition(Game.joysticks.getPosition(Game.joysticks.MOVEMENT).scale(delta*speed));
        currentAttack.update(delta);
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
    }
}
