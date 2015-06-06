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
        System.out.println(name + " is taking turn");
        if (intendedDirection >= 0) {
            move(intendedDirection);
            intendedDirection = Constants.NO_DIRECTION;
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

    public void calculateMove(Map map) {

    }
}

