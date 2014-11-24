package game.tile.arena.util;

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

    public Position(double dir) {
        this(Math.cos(dir), Math.sin(dir));
    }

    public Position scale(float scale) { return new Position(x*scale, y*scale); }
    public Position scale(double scale) { return scale((float)scale); }
    public Position subtract(Position pos) { return new Position(x-pos.x, y-pos.y); }
    public Position subtract(float nx, float ny) { return new Position(x-nx, y-ny); }
    public Position add(Position pos) { return new Position(x+pos.x, y+pos.y); }
    public Position add(float nx, float ny) { return new Position(x+nx, y+ny); }

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

    public double getDirTo(Position pos) {
        return Math.atan2(pos.y-y, pos.x-x);
    }

    public boolean inWorld(int padding) {
        return x>-padding && y>-padding && x<Game.WORLD.x+padding && y<Game.WORLD.y+padding;
    }

    public boolean inView(int pad) {
        float cx = Game.camera.position.x;
        float cy = Game.camera.position.y;

        float sx = Game.SCREEN.x/2;
        float sy = Game.SCREEN.y/2;

        return x>cx-sx-pad && y>cy-sy-pad && x<cx+sx+pad && y<cy+sy+pad;
    }

    public Position scaleY(double sy) {
        return new Position(x, y*sy);
    }

    public double magnitude() {
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public Position invertX() {
        return new Position(-x, y);
    }
}
