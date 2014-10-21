package game.tile.arena.entity.attack;

import game.tile.arena.util.Position;

public interface Attack {

    public void update(int delta, Position target);
}
