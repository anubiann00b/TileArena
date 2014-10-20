package game.tile.arena;

import java.util.LinkedList;
import java.util.List;

import game.tile.arena.entity.Entity;
import game.tile.arena.entity.projectile.Projectile;
import game.tile.arena.util.input.InputManager;
import game.tile.arena.util.joystick.JoystickManager;

public class Game {

    public static InputManager input = InputManager.getInstance();
    public static JoystickManager joysticks = JoystickManager.getInstance();

    public static List<Projectile> projectiles = new LinkedList<Projectile>();
    public static List<Entity> objects = new LinkedList<Entity>();
}
