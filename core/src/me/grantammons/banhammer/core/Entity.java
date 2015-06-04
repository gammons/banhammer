package me.grantammons.banhammer.core;

/**
 * Created by grantammons on 5/31/15.
 */
public class Entity {
    public int x;
    public int y;

    public void move(int direction) {
        switch(direction) {
            case Constants.LEFT: x--; break;
            case Constants.RIGHT: x++; break;
            case Constants.UP: y++; break;
            case Constants.DOWN: y--; break;
        }
    }
}
