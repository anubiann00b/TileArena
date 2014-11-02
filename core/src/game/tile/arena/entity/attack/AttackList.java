package game.tile.arena.entity.attack;

import java.util.LinkedList;

public class AttackList extends LinkedList<Attack> {

    public Attack getWrap(int index) {
        return get(getWrapIndex(index));
    }
    public int getWrapIndex(int index) {
        if (index < 0)
            return size();
        if (index > this.size()-1)
            return 0;
        return index;
    }
}
