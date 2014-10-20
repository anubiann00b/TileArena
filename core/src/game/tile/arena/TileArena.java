package game.tile.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Collections;
import java.util.Iterator;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.Player;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;

public class TileArena extends ApplicationAdapter {

    long last;
    SpriteBatch batch;

	@Override
	public void create() {
        last = System.currentTimeMillis();

        Game.input.init();
        Game.joysticks.init();

		batch = new SpriteBatch();

        Game.objects.add(new Player(new Position(400, 400)));
	}

	@Override
	public void render() {
        long temp = System.currentTimeMillis();
        int delta = (int)(temp-last);

        for (Entity o : Game.objects)
            o.update(delta);
        for (Iterator<Projectile> it = Game.projectiles.listIterator(); it.hasNext();) {
            Projectile p = it.next();
            boolean inScreen = p.update(delta);
            if (!inScreen)
                it.remove();
        }
        last = temp;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

        Collections.sort(Game.objects);
        for (Entity o : Game.objects)
            o.render(batch, delta);
        for (Projectile p : Game.projectiles)
            p.render(batch);

        Game.joysticks.render(batch, delta);

		batch.end();
	}
}
