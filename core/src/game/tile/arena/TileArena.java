package game.tile.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Collections;
import java.util.Iterator;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.enemy.Enemy;
import game.tile.arena.entity.enemy.ai.EnemyPlayerAI;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.MathHelper;
import game.tile.arena.util.Position;

public class TileArena extends ApplicationAdapter {

    SpriteBatch batch;
    BitmapFont font;

	@Override
	public void create() {
        font = new BitmapFont();

        Game.hudCam.position.set(Game.SCREEN.x/2, Game.SCREEN.y/2, 0);
        Game.hudCam.update();

		batch = new SpriteBatch();

        Game.objects.add(Game.player);

        for (int i=0;i<50;i++)
            Game.objects.add(new Enemy("blob_green",
                    new Position(Math.random()*(Game.WORLD.x-256)+128, Math.random()*(Game.WORLD.y-256)+128),
                    new EnemyPlayerAI()));
	}

    int cnt = 0;

	@Override
	public void render() {
        if (cnt++ < 120)
            return;

        double delta = 1;
        if (Gdx.graphics.getFramesPerSecond() != 0)
            delta = (double) Game.FPS/Gdx.graphics.getFramesPerSecond();

        update(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(Game.camera.combined);
        batch.begin();
        Collections.sort(Game.objects);
        Game.world.render(batch);
        for (Entity o : Game.objects)
            o.render(batch, delta);
        batch.setColor(1f, 1f, 1f, 1f);
        for (Projectile p : Game.projectiles)
            p.render(batch);

        batch.setProjectionMatrix(Game.hudCam.combined);
        Game.joysticks.render(batch, delta);

        font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 20, 30);

        batch.end();
        batch.setColor(1f, 1f, 1f, 1f);
	}

    private void update(double delta) {
        for (Entity o : Game.objects)
            o.update(delta);
        for (Iterator<Projectile> it = Game.projectiles.listIterator(); it.hasNext();) {
            Projectile p = it.next();
            boolean inScreen = p.update(delta);
            if (!inScreen)
                it.remove();
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
