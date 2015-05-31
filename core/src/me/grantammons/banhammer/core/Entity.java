package me.grantammons.banhammer.core;

/**
 * Created by grantammons on 5/31/15.
 */
public class Entity {
    public int x;
    public int y;

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveUp() {
        y++;
    }

    public void moveDown() {
        y--;
    }
}
