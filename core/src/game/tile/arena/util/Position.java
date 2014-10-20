package game.tile.arena.util;

import com.badlogic.gdx.Gdx;

import game.tile.arena.Game;

public class Position {

    public final float x;
    public final float y;

    public Position(double x, double y) {
        this((float)x, (float)y);
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(int scale) {
        this(scale, scale);
    }

    public Position scale(float scale) { return new Position(x*scale, y*scale); }
    public Position subtract(Position pos) { return new Position(x-pos.x, y-pos.y); }
    public Position add(Position pos) { return new Position(x+pos.x, y+pos.y); }

    public boolean inRange(float dist, Position pos) {
        return (pos.x-x)*(pos.x-x) + (pos.y-y)*(pos.y-y) < dist*dist;
    }

    public Position limit(float dist, Position pos) {
        if (inRange(dist, pos))
            return pos;

        Position offset = new Position(x-pos.x, y-pos.y);
        float distance = (float)Math.sqrt(offset.x*offset.x + offset.y*offset.y);
        return this.subtract(offset.scale(dist / distance));
    }

    public Position addPolar(Position pos) {
        return new Position(x+pos.y*Math.cos(pos.x), y+pos.y*Math.sin(pos.x));
    }

    public boolean isZero() {
        return x==0 && y==0;
    }

    public double getDir() {
        return Math.atan2(y, x);
    }

    public boolean inWorld(int padding) {
        return x>-padding && y>-padding && x<Game.WORLD.x+padding && y<Game.WORLD.y+padding;
    }

    public boolean inView(int padding) {
        return x>-padding && y>-padding && x<Game.SCREEN.x+padding && y<Game.SCREEN.y+padding;
    }

    public Position scaleY(double sy) {
        return new Position(x, y*sy);
    }
}
