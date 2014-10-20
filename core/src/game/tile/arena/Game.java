package game.tile.arena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.Player;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.Position;
import game.tile.arena.util.input.InputManager;
import game.tile.arena.util.joystick.JoystickManager;
import game.tile.arena.world.World;

public class Game {

    public static Position WORLD = new Position(Gdx.graphics.getWidth()*3, Gdx.graphics.getHeight()*3);
    public static Position SCREEN = new Position(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    public static OrthographicCamera camera = new OrthographicCamera(SCREEN.x, SCREEN.y);

    public static World world = World.getInstance();

    public static InputManager input = InputManager.getInstance();
    public static JoystickManager joysticks = JoystickManager.getInstance();

    public static Player player = new Player(new Position(0, 0));
    public static List<Projectile> projectiles = new LinkedList<Projectile>();
    public static List<Entity> objects = new LinkedList<Entity>();
}
