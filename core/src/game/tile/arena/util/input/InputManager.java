package game.tile.arena.util.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager {

    private static InputManager inputManager;

    public static InputManager getInstance() {
        if (inputManager == null) {
            inputManager = new InputManager();
            return inputManager;
        }
        return null;
    }

    private InputManager() { }

    private InputMultiplexer inputs;

    public void addInputProcessor(InputProcessor in) {
        inputs.addProcessor(in);
    }

    public void init() {
        inputs = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputs);
    }
}
