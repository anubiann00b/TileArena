package game.tile.arena.entity.attack;

import game.tile.arena.util.Position;

public interface Attack {

    public void equip();
    public void dequip();
    public void update(int delta, Position target, boolean orientation);
}
