package me.grantammons.rogueEngine.core.entities.mobs;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

public abstract class Mob extends AnimatedEntity {
    public Mob(Notifier notifier) {
        super(notifier);
    }

    public void calculateMove(Map map) {
        boolean moveFound = false;
        for(int x = 0; x < 10; x++) {
            int intendedDirection = MathUtils.random(3);
            intendedLocation = Location.setLocationFromDirection(location, intendedDirection);
            if (map.canMove(intendedLocation)) {
                moveFound = true;
            }
        }

        if (!moveFound) intendedLocation = location;
    }

    public boolean hasAI() {
        return true;
    }
}
