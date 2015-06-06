package me.grantammons.banhammer.core;

/**
 * Created by grantammons on 5/31/15.
 */
public class Entity {
    public int x;
    public int y;

    public String name;
    public int speed;
    public int energy;
    public int intendedDirection = -1;

    public void takeTurn() {
        if (intendedDirection >= 0) {
            move(intendedDirection);
            intendedDirection = -1;
        } else {
            // do some other shit
        }
    }

    public void move(int direction) {
        switch (direction) {
            case Constants.LEFT:
                x--;
                break;
            case Constants.RIGHT:
                x++;
                break;
            case Constants.UP:
                y++;
                break;
            case Constants.DOWN:
                y--;
                break;
        }
    }
}

