package game.tile.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Collections;
import java.util.Iterator;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.MathHelper;

public class TileArena extends ApplicationAdapter {

    long last;
    SpriteBatch batch;

	@Override
	public void create() {
        last = System.currentTimeMillis();

        Game.hudCam.position.set(Game.SCREEN.x/2, Game.SCREEN.y/2, 0);
        Game.hudCam.update();

		batch = new SpriteBatch();

        Game.objects.add(Game.player);
	}

	@Override
	public void render() {
        long temp = System.currentTimeMillis();
        int delta = (int)(temp-last);
        update(delta);
        last = temp;

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(Game.camera.combined);
        batch.begin();
        Collections.sort(Game.objects);
        Game.world.render(batch);
        for (Entity o : Game.objects)
            o.render(batch, delta);
        for (Projectile p : Game.projectiles)
            p.render(batch);

        batch.setProjectionMatrix(Game.hudCam.combined);
        Game.joysticks.render(batch, delta);

		batch.end();
	}

    private void update(int delta) {
        for (Entity o : Game.objects)
            o.update(delta);
        for (Iterator<Projectile> it = Game.projectiles.listIterator(); it.hasNext();) {
            Projectile p = it.next();
            boolean inScreen = p.update(delta);
            if (!inScreen) {
                it.remove();
            }
        }
        updateCameraPosition();
        Game.camera.update();
    }

    private void updateCameraPosition() {
        float minX = Game.SCREEN.x / 2;
        float maxX = Game.WORLD.x - minX;
        float minY = Game.SCREEN.y / 2;
        float maxY = Game.WORLD.y - minY;
        Game.camera.position.set(
                MathHelper.median(minX, Game.player.pos.x, maxX),
                MathHelper.median(minY, Game.player.pos.y, maxY),
                0);
    }
}
