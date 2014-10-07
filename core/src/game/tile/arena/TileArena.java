package game.tile.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.tile.arena.object.GameObject;
import game.tile.arena.object.Player;
import game.tile.arena.util.Position;

public class TileArena extends ApplicationAdapter {

	SpriteBatch batch;
    List<GameObject> objects;
    long last;

	@Override
	public void create() {
		batch = new SpriteBatch();
        objects = new ArrayList<GameObject>();

        objects.add(new Player(new Position(32, 32)));
	}

	@Override
	public void render() {
        long temp = System.currentTimeMillis();
        for (GameObject o : objects)
            o.update((int)(temp-last));
        last = temp;

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        Collections.sort(objects);
        for (GameObject o : objects)
            o.render(batch);

		batch.end();
	}
}
