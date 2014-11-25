package game.tile.arena.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.tile.arena.TileArena;
import game.tile.arena.util.Preferences;

public class DesktopLauncher {

	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        Preferences.get().setTouch(false);
		new LwjglApplication(new TileArena(), config);
	}
}
