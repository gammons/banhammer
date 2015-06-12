package me.grantammons.banhammer.core.entities
import me.grantammons.banhammer.core.Constants
import me.grantammons.banhammer.core.Map

class Entity {
    int x
    int y
    String name
    int speed
    int intendedDirection = Constants.NO_DIRECTION

    def takeTurn(Map map, List<Entity> entities) {
        if (intendedDirection >= 0) {
            switch(true)
            {
                case { map.canDig(this, intendedDirection) }:
                    map.dig(this, intendedDirection)
                    break;
//                case { canHitEntity(entities) }:
//                    println "can hit enemy"
                case { map.canMove(this, intendedDirection)}:
                    move(intendedDirection)
            }
        }
    }

    def canHitEntity(List<Entity> entities) {
        return false;
    }

    def move(int direction) {
        switch (direction) {
            case Constants.WEST: x--; break
            case Constants.EAST: x++; break
            case Constants.NORTH: y++; break
            case Constants.SOUTH: y--; break
            case Constants.NORTHEAST:
                y++
                x++
                break
            case Constants.NORTHWEST:
                y++
                x--
                break
            case Constants.SOUTHEAST:
                y--
                x++
                break
            case Constants.SOUTHWEST:
                y--
                x--
                break
        }
    }

    def calculateMove(Map map) {

    }
}

