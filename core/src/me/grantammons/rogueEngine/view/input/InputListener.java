package me.grantammons.rogueEngine.view.input;

/**
 * Created by grantammons on 5/31/15.
 */
public interface InputListener {
    void notify(int direction);
    void notifyMouseMoved(int screenX, int screenY);
    void notifyMouseClicked(int screenX, int screenY, int button);

    void notifyMouseScrollDown();
    void notifyMouseScrollUp();
}
