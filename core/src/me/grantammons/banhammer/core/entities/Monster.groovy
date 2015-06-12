package me.grantammons.banhammer.core.entities

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.banhammer.core.Constants;
import me.grantammons.banhammer.core.Map;

abstract class Monster extends Entity {
    def calculateMove(Map map) {

        setIntendedDirection(Constants.NO_DIRECTION)
        (0..10).each { n ->
            def direction = MathUtils.random(3)
            if (map.canMove(this, direction)) {
                setIntendedDirection(direction)
            }
        }
    }
}
