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

    private Position translate(Position pos) {
        return new Position(x-pos.x, y-pos.y);
    }

    public boolean inRange(float dist, Position pos) {
        return (pos.x-x)*(pos.x-x) + (pos.y-y)*(pos.y-y) < dist*dist;
    }

    public Position limit(float dist, Position pos) {
        if (inRange(dist, pos))
            return pos;

        Position offset = new Position(x-pos.x, y-pos.y);
        float distance = (float)Math.sqrt(offset.x*offset.x + offset.y*offset.y);
        return this.translate(offset.scale(dist/distance));
    }
}
