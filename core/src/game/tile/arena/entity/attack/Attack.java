package game.tile.arena.entity.attack;

import game.tile.arena.util.Position;

public interface Attack {

    public abstract void update(int delta, Position target, boolean orientation);
}
