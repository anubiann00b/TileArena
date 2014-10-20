package game.tile.arena.util;

public class MathHelper {

    public static float median(float a, float b, float c) {
        return (a<=b)?((b<=c)?b:((a<c)?c:a)):((a<=c)?a:((b<c)?c:b));
    }
}
