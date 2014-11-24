package game.tile.arena.util.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.tile.arena.util.Position;

public class TouchController extends PlayerController {

    TouchController() {
        sticks = new Joystick[2];
        sticks[MOVEMENT] = new Joystick(
                new Position(Gdx.graphics.getWidth()*0.25, Gdx.graphics.getHeight()*0.25),
                100, "circle", "joystick_bg");
        sticks[ATTACK] = new Joystick(
                new Position(Gdx.graphics.getWidth()*0.75, Gdx.graphics.getHeight()*0.25),
                100, "circle", "joystick_bg");
    }

    public Joystick[] sticks;
    public final int MOVEMENT = 0;
    public final int ATTACK = 1;

    public Position getMovement() {
        return sticks[MOVEMENT].getStickPosition();
    }

    public Position getAttack() {
        return sticks[ATTACK].getStickPosition();
    }

    public void render(SpriteBatch batch, double delta) {
        sticks[MOVEMENT].renderStick(batch, delta);
        sticks[ATTACK].renderStick(batch, delta);
    }
}