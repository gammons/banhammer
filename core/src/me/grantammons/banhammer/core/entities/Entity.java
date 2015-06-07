package me.grantammons.banhammer.core.entities;

import me.grantammons.banhammer.core.Constants;
import me.grantammons.banhammer.core.Map;

/**
 * Created by grantammons on 5/31/15.
 */

public class Entity {
    public int x;
    public int y;

    public String name;
    public int speed;
    public int intendedDirection = Constants.NO_DIRECTION;

    public void takeTurn() {
        if (intendedDirection >= 0) {
            move(intendedDirection);
        } else {
            // do some other shit
        }
    }

    public void move(int direction) {
        switch (direction) {
            case Constants.WEST:
                x--;
                break;
            case Constants.EAST:
                x++;
                break;
            case Constants.NORTH:
                y++;
                break;
            case Constants.SOUTH:
                y--;
                break;
            case Constants.NORTHEAST:
                y++;
                x++;
                break;
            case Constants.NORTHWEST:
                y++;
                x--;
                break;
            case Constants.SOUTHEAST:
                y--;
                x++;
                break;
            case Constants.SOUTHWEST:
                y--;
                x--;
                break;
        }
    }

    public void calculateMove(Map map) {

    }
}

