package game.tile.arena.sprite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.tile.arena.Game;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class ImageMask {

    private boolean[][] mask;
    private final int xOff;
    private final int yOff;
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public ImageMask(TextureRegion region, Pixmap map) {
        mask = simplify(getMaskFromTexture(region, map));
        xOff = -4*(mask.length/2);
        yOff = -4*(mask[0].length/2+1);
    }

    public boolean[][] getMask() { return mask; }

    public boolean intersects(ImageMask mask, Position pos, Position other) {
        float x = pos.x + xOff;
        float y = pos.y + yOff;
        boolean[][] otherMask = mask.getMask();
        if ((x+this.mask.length*4<other.x)
                || y+ this.mask[0].length*4<other.y
                || other.x+otherMask.length*4<x
                || other.y+otherMask[0].length*4<y)
            return false;

        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;

        for (int i=0;i<this.mask.length;i++) {
            for (int j=0;j<this.mask[i].length;j++) {
                if (!this.mask[i][j])
                    continue;
                for (int k=0;k<otherMask.length;k++) {
                    for (int l=0;l<otherMask[k].length;l++) {
                        if (!otherMask[k][l])
                            continue;
                        if (x+i*4<=other.x+k*4+4)
                            right = true;
                        if(x+i*4+4>=other.x+k*4)
                            left = true;
                        if (y+j*4<=other.y+l*4+4)
                            down = true;
                        if (y+j*4+4>=other.y+l*4)
                            up = true;
                        if (up && down && left && right)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return toString(this.mask);
    }

    public String toString(boolean[][] array) {
        StringBuilder s = new StringBuilder();
        for (boolean[] subArray : array) {
            for (boolean val : subArray) {
                s.append(val ? "X" : " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void render(Position pos) {
        if (!Game.DEBUG)
            return;
        shapeRenderer.setProjectionMatrix(Game.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i=0;i<mask.length;i++)
            for (int j=0;j<mask[i].length;j++)
                if (mask[i][j])
                    shapeRenderer.rect(pos.x+4*i+xOff,pos.y+4*j+yOff,4,4);
        shapeRenderer.end();
    }

    private boolean[][] getMaskFromTexture(TextureRegion region, Pixmap pixmap) {
        boolean[][] imageMask = new boolean[region.getRegionWidth()][region.getRegionHeight()];
        Color color = new Color();

        for (int i=0;i<imageMask.length;i++) {
            for (int j=1;j<=imageMask[i].length;j++) {
                int pixel = pixmap.getPixel(i+region.getRegionX(), j+region.getRegionY());
                Color.rgba8888ToColor(color, pixel);
                imageMask[i][imageMask[i].length-j] = color.a != 0;
            }
        }
        return imageMask;
    }

    private boolean[][] simplify(boolean[][] startMask) {
        int width = startMask.length;
        int height = startMask[0].length;

        boolean[][] simplifiedMask = new boolean[width][height];

        for (int i=0;i<width;i++) {
            System.arraycopy(startMask[i], 0, simplifiedMask[i], 0, height);
        }

        for (int i=1;i<width-1;i++) {
            for (int j=1;j<height-1;j++) {
                simplifiedMask[i][j] = startMask[i][j]
                        && !((startMask[i+1][j])
                        && (startMask[i+1][j-1])
                        && (startMask[i+1][j+1])
                        && (startMask[i][j+1])
                        && (startMask[i-1][j+1])
                        && (startMask[i-1][j])
                        && (startMask[i-1][j-1])
                        && (startMask[i][j-1]));
            }
        }
        return simplifiedMask;
    }

    public boolean intersects(Projectile p, Position pos) {
        float px = p.pos.x;
        float py = p.pos.y;
        float x = pos.x + xOff;
        float y = pos.y + yOff;
        for (int i=0;i<this.mask.length;i++) {
            for (int j=0;j<this.mask[i].length;j++) {
                if (!this.mask[i][j])
                    continue;
                if (px > i*4+x && px-4 < i*4+x && py > j*4+y && py-4 < j*4+y)
                    return true;
            }
        }
        return false;
    }

    public void projectileCollisionRender(SpriteBatch batch, Projectile p) {
        if (!Game.DEBUG)
            return;
        float px = p.pos.x;
        float py = p.pos.y;

        batch.end();
        shapeRenderer.setProjectionMatrix(Game.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.rect(px-4, py-4, 4, 4);

        shapeRenderer.end();
        batch.begin();
    }
}
