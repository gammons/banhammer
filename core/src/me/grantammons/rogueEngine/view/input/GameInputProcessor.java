package me.grantammons.rogueEngine.view.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import me.grantammons.rogueEngine.core.Constants;

import java.util.ArrayList;
import java.util.List;

public class GameInputProcessor implements InputProcessor {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    private boolean downLeftPressed;
    private boolean downRightPressed;
    private boolean upLeftPressed;
    private boolean upRightPressed;

    private List<InputListener> listeners;

    public GameInputProcessor() {
        listeners = new ArrayList<InputListener>();
    }

    public void addListener(InputListener listener) {
        listeners.add(listener);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
            case Keys.NUM_4:
            case Keys.H:
                if (leftPressed == false)
                    notifyListeners(Constants.WEST);
                leftPressed = true;
                break;
            case Keys.RIGHT:
            case Keys.NUM_6:
            case Keys.L:
                if (rightPressed == false)
                    notifyListeners(Constants.EAST);
                rightPressed = true;
                break;
            case Keys.UP:
            case Keys.NUM_8:
            case Keys.K:
                if (upPressed == false)
                    notifyListeners(Constants.NORTH);
                upPressed = true;
                break;
            case Keys.DOWN:
            case Keys.NUM_2:
            case Keys.J:
                if (downPressed == false)
                    notifyListeners(Constants.SOUTH);
                downPressed = true;
                break;
            case Keys.NUM_1:
            case Keys.B:
                if (downLeftPressed == false)
                    notifyListeners(Constants.SOUTHWEST);
                downLeftPressed = true;
                break;
            case Keys.NUM_3:
            case Keys.N:
                if (downRightPressed == false)
                    notifyListeners(Constants.SOUTHEAST);
                downRightPressed = true;
                break;
            case Keys.NUM_7:
            case Keys.Y:
                if (upLeftPressed == false)
                    notifyListeners(Constants.NORTHWEST);
                upLeftPressed = true;
                break;
            case Keys.NUM_9:
            case Keys.U:
                if (upRightPressed == false)
                    notifyListeners(Constants.NORTHEAST);
                upRightPressed = true;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
            case Keys.NUM_4:
            case Keys.H:
                leftPressed = false;
                break;
            case Keys.RIGHT:
            case Keys.NUM_6:
            case Keys.L:
                rightPressed = false;
                break;
            case Keys.UP:
            case Keys.NUM_8:
            case Keys.K:
                upPressed = false;
                break;
            case Keys.DOWN:
            case Keys.NUM_2:
            case Keys.J:
                downPressed = false;
                break;
            case Keys.NUM_1:
            case Keys.B:
                downLeftPressed = false;
                break;
            case Keys.NUM_3:
            case Keys.N:
                downRightPressed = false;
                break;
            case Keys.NUM_7:
            case Keys.Y:
                upLeftPressed = false;
                break;
            case Keys.NUM_9:
            case Keys.U:
                upRightPressed = false;
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (InputListener listener : listeners) {
            listener.notifyMouseMoved(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
    private void notifyListeners(int direction) {
        for (InputListener listener : listeners) {
            listener.notify(direction);
        }
    }
}
