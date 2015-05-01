package game.tile.arena.util;

import game.tile.arena.Game;

public class Position {

    public final float x;
    public final float y;

    public Position() {
        this(0, 0);
    }

    public Position(double x, double y) {
        this((float)x, (float)y);
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a unit vector in the specified direction.
     *
     * @param dir A direction, in radians.
     */
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
        return getDistance(pos) < dist*dist;
    }

    public float getDistance(Position pos) {
        return (float) Math.sqrt((pos.x-x)*(pos.x-x) + (pos.y-y)*(pos.y-y));
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

    public Position normalize(float scale) {
        scale /= Math.max(Math.abs(x),Math.abs(y));
        return new Position(x*scale, y*scale);
    }

    public Position normalize() {
        return normalize(1);
    }

    /**
     * Given a vector with position p and direction dir, and a point e, this method
     * returns the orthogonal vector to p that intersects e.
     *
     * @param p The position of the vector
     * @param e The position of the point
     * @param theta The direction of the vector, in radians
     * @return The orthogonal vector to p that goes through e
     */
    public static Position findOrthogonalVector(Position p, Position e, float theta) {
        double normalTheta = theta + Math.PI/2;
        double distance = (e.x - p.x)*Math.cos(normalTheta) + (e.y - p.y)*Math.sin(normalTheta);
        if (distance < 0) { // That means the normal vector is in the wrong direction (towards e instead of away)
            normalTheta = theta - Math.PI/2;
            distance = -distance;
        }
        return new Position(normalTheta).scale(-distance);
    }
}
