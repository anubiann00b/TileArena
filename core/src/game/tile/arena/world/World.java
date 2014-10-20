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
        tiles = new Tile[(int)Game.WORLD.x/64][(int)Game.WORLD.y/64];

        for (int i=0;i<tiles.length;i++) {
            for (int j=0;j<tiles[0].length;j++) {
                tiles[i][j] = Tile.GRASS;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i=0;i<tiles.length;i++) {
            for (int j=0;j<tiles[0].length;j++) {
                tiles[i][j].render(batch, new Position(i*64, j*64));
            }
        }
    }
}
