package me.grantammons.rogueEngine.core.entities.AIs;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

import java.util.ArrayList;

public class RandomAI extends AI implements AIable {

    public RandomAI(AnimatedEntity entity) {
        super(entity);
    }

    @Override
    public void calculateMove(Map map) {
        for(int x = 0; x < 10; x++) {
            int intendedDirection = MathUtils.random(3);
            Location location = Location.setLocationFromDirection(entity.location, intendedDirection);
            if (map.canMove(location)) {
                ArrayList<Location> path = new ArrayList<>();
                path.add(entity.location);
                path.add(location);
                entity.setPath(path);
            }
        }
    }
}
