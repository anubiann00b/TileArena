package game.tile.arena.util;

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

    private boolean touched;
    private boolean released;
    private boolean pressed;
    private boolean held;

    private Position currentPosition;

    public boolean isTouched() { return touched; }
    public boolean isReleased() { return released; }
    public boolean isPressed() { return pressed; }
    public boolean isHeld() { return held; }
    public Position getCursor() { return currentPosition; }

    private InputMultiplexer inputs;

    public void addInputProcessor(InputProcessor in) {
        inputs.addProcessor(in);
    }

    public void init() {
        inputs = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputs);
    }

    public void update() {
        boolean nTouched = Gdx.input.isTouched();

        pressed = nTouched && !touched;
        released = touched && !nTouched;
        held = nTouched && touched;

        currentPosition = new Position(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());

        touched = nTouched;
    }
}
