package game.tile.arena.util.controller;

import game.tile.arena.Game;
import game.tile.arena.util.Position;
import game.tile.arena.util.input.KeyboardMoveInput;

public class KeyboardController extends PlayerController {

    Position movementPos;
    Position attackPos;

    KeyboardController() {
        movementPos = new Position(0,0);
        attackPos = new Position(0,0);
        Game.rawInput.addInputProcessor(new KeyboardMoveInput(this));
        Game.rawInput.addInputProcessor(new MouseAttackInput(this));
    }

    @Override
    public Position getMovement() {
        return movementPos;
    }

    @Override
    public Position getAttack() {
        return attackPos;
    }

    public void keyPressed(int dir) {
        switch (dir) {
            case 0:
                movementPos = movementPos.add(1,0);
                break;
            case 1:
                movementPos = movementPos.add(0,1);
                break;
            case 2:
                movementPos = movementPos.add(-1,0);
                break;
            case 3:
                movementPos = movementPos.add(0,-1);
                break;
        }
    }

    public void keyReleased(int dir) {
        keyPressed((dir+2)%4);
    }

    public boolean mouseClick(int x, int y, int pointer, int button) {
        mouseClick(x, y, pointer);
        return true;
    }

    public boolean mouseClick(int x, int y, int pointer) {
        attackPos = Game.player.pos.subtract(x, y).subtract(Game.getCameraPos().subtract(Game.SCREEN.scale(1/2.0))).invertX();
        return true;
    }

    public boolean mouseUp(int x, int y, int button) {
        attackPos = new Position(0, 0);
        return true;
    }
}