package game.tile.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.Player;
import game.tile.arena.util.Position;

public class TileArena extends ApplicationAdapter {

	SpriteBatch batch;
    List<Entity> objects;
    long last;

	@Override
	public void create() {
        last = System.currentTimeMillis();

		batch = new SpriteBatch();
        objects = new ArrayList<Entity>();

        objects.add(new Player("player", 166));
	}

	@Override
	public void render() {
        long temp = System.currentTimeMillis();
        int delta = (int)(temp-last);
        for (Entity o : objects)
            o.update(delta);
        last = temp;

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        Collections.sort(objects);
        for (Entity o : objects)
            o.render(batch, delta);

		batch.end();
	}
}
