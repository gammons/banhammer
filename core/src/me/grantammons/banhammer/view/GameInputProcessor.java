package me.grantammons.banhammer.view;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameInputProcessor implements InputProcessor {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;

    private boolean mousePressed;
    private Vector2 mouseCoords;
    private int windowHeight;
    private int windowWidth;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.LEFT:
                leftPressed = true;
                break;
            case Keys.RIGHT:
                rightPressed = true;
                break;
            case Keys.UP:
                upPressed = true;
                break;
            case Keys.DOWN:
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
        mouseCoords = new Vector2(screenX, screenY);
        mousePressed = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        mousePressed = false;
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

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public Vector2 getMouseCoords(OrthographicCamera c) {
        Vector3 v = new Vector3(mouseCoords.x, mouseCoords.y, 0);
        Vector3 unprojected = c.unproject(v);
        return new Vector2(unprojected.x, unprojected.y);
    }

    public void setWindow(int width, int height) {
        windowHeight = height;
        windowWidth = width;

    }
}
