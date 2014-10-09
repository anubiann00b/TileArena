package game.tile.arena.util;

public class Position {

    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position scale(float scale) {
        return new Position(x*scale, y*scale);
    }

    public boolean inRange(float dist, Position pos) {
        return dist*dist < (pos.x-x)*(pos.x-x) + (pos.y-y)*(pos.y-y);
    }

    public Position limit(float dist, Position pos) {
        if (inRange(dist, pos))
            return pos;

        Position offset = new Position(x-pos.x, y-pos.y);
        float distance = (float)Math.sqrt(offset.x*offset.x + offset.y*offset.y);
        return offset.scale(distance/dist);
    }
}
