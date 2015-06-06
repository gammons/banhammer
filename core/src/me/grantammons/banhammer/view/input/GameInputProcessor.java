package me.grantammons.banhammer.view.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import me.grantammons.banhammer.view.input.InputListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameInputProcessor implements InputProcessor {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

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
                if (leftPressed == false)
                    notifyListeners(Keys.LEFT);
                leftPressed = true;
                break;
            case Keys.RIGHT:
                if (rightPressed == false)
                    notifyListeners(Keys.RIGHT);
                rightPressed = true;
                break;
            case Keys.UP:
                if (upPressed == false)
                    notifyListeners(Keys.UP);
                upPressed = true;
                break;
            case Keys.DOWN:
                if (downPressed == false)
                    notifyListeners(Keys.DOWN);
                downPressed = true;
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                leftPressed = false;
                break;
            case Keys.RIGHT:
                rightPressed = false;
                break;
            case Keys.UP:
                upPressed = false;
                break;
            case Keys.DOWN:
                downPressed = false;
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
    private void notifyListeners(int direction) {
        Iterator<InputListener> iterator  = listeners.iterator();
        while (iterator.hasNext()) {
            InputListener listener = iterator.next();
            listener.notify(direction);
        }
    }
}
