package game.tile.arena.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import game.tile.arena.util.Position;
import game.tile.arena.util.SpriteSheet;

public abstract class GameObject implements Comparable<GameObject> {

    public Position pos;
    protected List<SpriteSheet> sprites;

    public GameObject(Position p) {
        pos = p;
        sprites = new ArrayList<SpriteSheet>();
    }

    public abstract void update(int delta);
    public abstract void render(SpriteBatch batch);

    @Override
    public int compareTo(GameObject o) {
        return pos.y<o.pos.y ? -1 : (pos.y==o.pos.y ? 0 : 1);
    }
}
