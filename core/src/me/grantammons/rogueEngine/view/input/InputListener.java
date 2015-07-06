package me.grantammons.rogueEngine.view.input;

/**
 * Created by grantammons on 5/31/15.
 */
public interface InputListener {
    void notify(int direction);
    void notifyMouseMoved(int screenX, int screenY);
}
