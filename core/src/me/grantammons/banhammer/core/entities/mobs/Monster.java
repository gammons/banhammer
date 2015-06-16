package me.grantammons.banhammer.core.entities.mobs;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.banhammer.core.Location;
import me.grantammons.banhammer.core.Map;
import me.grantammons.banhammer.core.Notifier;
import me.grantammons.banhammer.core.entities.Entity;

public abstract class Monster extends Entity {
    public Monster(Notifier notifier) {
        super(notifier);
    }

    public void calculateMove(Map map) {
        boolean moveFound = false;
        for(int x = 0; x < 10; x++) {
            intendedDirection = MathUtils.random(3);
            intendedLocation = Location.setLocationFromDirection(location, intendedDirection);
            if (map.canMove(intendedLocation)) {
                moveFound = true;
            }
        }

        if (!moveFound) intendedLocation = location;
    }
}
