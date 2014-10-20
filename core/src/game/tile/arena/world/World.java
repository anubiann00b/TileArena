package game.tile.arena.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.Game;
import game.tile.arena.util.Position;

public class World {

    private static World world;

    public static World getInstance() {
        if (world == null) {
            world = new World();
            return world;
        }
        return null;
    }

    public Tile[][] tiles;

    private World() {
        tiles = new Tile[(int)Game.WORLD.x/64+1][(int)Game.WORLD.y/64+1];
        for (int i=0;i<tiles.length;i++) {
            for (int j=0;j<tiles[0].length;j++) {
                tiles[i][j] = Tile.GRASS;
            }
        }
    }

    public void render(SpriteBatch batch) {
        float cx = Game.camera.position.x;
        float cy = Game.camera.position.y;

        float sx = Game.SCREEN.x/2;
        float sy = Game.SCREEN.y/2;

        for (int i=(int)(cx-sx)/64;i<(int)(cx+sx)/64+1;i++) {
            for (int j=(int)(cy-sy)/64;j<(int)(cy+sy)/64+1;j++) {
                tiles[i][j].render(batch, new Position(i*64, j*64));
            }
        }
    }
}
