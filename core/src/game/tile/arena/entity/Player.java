package game.tile.arena.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.Game;
import game.tile.arena.entity.attack.Attack;
import game.tile.arena.entity.attack.AttackBow;
import game.tile.arena.entity.attack.AttackBowMultishot;
import game.tile.arena.util.Position;
import game.tile.arena.util.input.WeaponSwitchInput;

public class Player extends Entity {

    private Attack currentAttack;
    private int currentAttackIndex = -1;
    private List<Attack> attackList = new LinkedList<Attack>();

    public Player(Position p) {
        super("player", p);
        addAttack(new AttackBowMultishot(50, 4, 25));
        addAttack(new AttackBow(20, 400, 5));
        addAttack(new AttackBow(400));
        Game.input.addInputProcessor(new WeaponSwitchInput());
    }

    public void switchWeapon() {
        if (currentAttackIndex == attackList.size()) {
            switchWeapon(0);
            currentAttackIndex = 0;
        } else {
            switchWeapon(currentAttackIndex++);
        }
    }

    public void switchWeapon(int index) {
        currentAttack.dequip();
        currentAttack = attackList.get(index);
        currentAttack.equip();
    }

    public void addAttack(Attack a) {
        if (currentAttack == null) {
            currentAttack = a;
            currentAttack.equip();
            currentAttackIndex = 0;
        }
        attackList.add(a);
    }

    @Override
    public void update(int delta) {
        updatePosition(Game.joysticks.getPosition(Game.joysticks.MOVEMENT).scale(delta*speed));
        currentAttack.update(delta, Game.joysticks.getPosition(Game.joysticks.ATTACK), Game.ALLY);
    }

    @Override
    public void render(SpriteBatch batch, int delta) {
        super.render(batch, delta);
    }
}
