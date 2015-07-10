package me.grantammons.rogueEngine.core.entities.AIs;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.entities.Entity;

public class RandomAI extends AI implements AIable {

    public RandomAI(Entity entity) {
        super(entity);
    }

    @Override
    public Location calculateMove(Map map) {
        Location location = entity.location;
        for(int x = 0; x < 10; x++) {
            int intendedDirection = MathUtils.random(3);
            location = Location.setLocationFromDirection(entity.location, intendedDirection);
            if (map.canMove(location))
                break;
        }
        return location;
    }
}
