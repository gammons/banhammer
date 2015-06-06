package me.grantammons.banhammer.core.entities;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.banhammer.core.Constants;
import me.grantammons.banhammer.core.Map;

/**
 * Created by grantammons on 6/5/15.
 */
public abstract class Monster extends Entity {
    public void calculateMove(Map map) {

        boolean moveFound = false;
        for(int x = 0; x < 10; x++) {
            intendedDirection = MathUtils.random(3);
            if (map.canMove(this, intendedDirection)) {
                moveFound = true;
                break;
            }
        }
        if (!moveFound) {
            intendedDirection = Constants.NO_DIRECTION;
        }
    }
}
