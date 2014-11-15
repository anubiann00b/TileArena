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

    public static boolean DISPLAY_JOYSTICKS;

    public static final int FPS = 60;

    public static final boolean ALLY = true;
    public static final boolean ENEMY = false;

    public static Position WORLD;
    public static Position SCREEN;

    public static OrthographicCamera camera;
    public static OrthographicCamera hudCam;

    public static World world;
    public static InputManager input;

    public static JoystickManager joysticks;
    public static Player player;
    public static List<Projectile> projectiles = new LinkedList<Projectile>();
    public static List<Entity> entities = new LinkedList<Entity>();

    public static void init() {
        WORLD = new Position(Gdx.graphics.getWidth()*3, Gdx.graphics.getHeight()*3);
        SCREEN = new Position(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera = new OrthographicCamera(SCREEN.x, SCREEN.y);
        hudCam = new OrthographicCamera(SCREEN.x, SCREEN.y);
        world = World.getInstance();
        input = InputManager.getInstance();
        joysticks = JoystickManager.getInstance();
        player = new Player(new Position(0, 0));
    }
}
