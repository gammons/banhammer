package me.grantammons.banhammer.core.entities;

import me.grantammons.banhammer.core.Constants;
import me.grantammons.banhammer.core.Location;
import me.grantammons.banhammer.core.Map;

import java.util.List;

/**
 * Created by grantammons on 5/31/15.
 */

public class Entity {
    public Location location;
    public Location intendedLocation;

    public String name;
    public int speed;
    public int intendedDirection = Constants.NO_DIRECTION;

    public void takeTurn(Map map, List<Entity> entities) {
        if (intendedDirection >= 0) {
            intendedLocation = Location.setLocationFromDirection(location, intendedDirection);
            if (map.canDig(intendedLocation)) {
                map.dig(intendedLocation);
            } else if (canHitEntity(entities)) {

            } else if (map.canMove(intendedLocation)) {
                move();
            }
        } else {
            // do some other shit
        }
    }

    private boolean canHitEntity(List<Entity> entities) {
        for (Entity e : entities) {
            if ((Math.abs(e.location.x - location.x) == 1) || (Math.abs(e.location.y - location.y) == 1)) {

            }
        }
        return false;
    }

    public int getSpeed() {
        return speed;
    }

    public void move() {
        location = intendedLocation;
    }

    public void calculateMove(Map map) {

    }
}

