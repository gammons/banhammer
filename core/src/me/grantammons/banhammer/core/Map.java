package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;

/**
 * Created by grantammons on 5/31/15.
 */
public class Map {
    private int[][] map = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public int[][] getMap() {
        return map;
    }

    public boolean canMove(Entity e, int direction) {
        switch (direction) {
            case Constants.WEST: return canMoveEast(e);
            case Constants.EAST: return canMoveWest(e);
            case Constants.NORTH: return canMoveNorth(e);
            case Constants.SOUTH: return canMoveSouth(e);
            case Constants.NORTHEAST:  return canMoveNorthEast(e);
            case Constants.NORTHWEST: return canMoveNorthWest(e);
            case Constants.SOUTHEAST: return canMoveSouthEast(e);
            case Constants.SOUTHWEST: return canMoveSouthWest(e);
        }
        return false;
    }

    private boolean canMoveEast(Entity e) {
        return e.x != 0 && map[e.y][e.x - 1] != 1;

    }

    private boolean canMoveNorthWest(Entity e) {
        if ((e.y + 1 == map.length) || (e.x - 1) < 0)
            return false;
        return map[e.y + 1][e.x - 1] != 1;
    }
    private boolean canMoveNorthEast(Entity e) {
        if (e.y + 1 == map.length || (e.x + 1 == map[e.y].length))
            return false;
        return map[e.y + 1][e.x + 1] != 1;
    }

    private boolean canMoveSouthWest(Entity e) {
        if ((e.y - 1 < 0) || (e.x - 1 < 0))
            return false;
        return map[e.y - 1][e.x - 1] != 1;
    }
    private boolean canMoveSouthEast(Entity e) {
        if ((e.y - 1 < 0) || (e.x + 1 == map[e.y].length))
            return false;
        return map[e.y - 1][e.x + 1] != 1;
    }

    private boolean canMoveWest(Entity e) {
        return e.x != map[e.y].length - 1 && map[e.y][e.x + 1] != 1;

    }

    private boolean canMoveSouth(Entity e) {
        return e.y != 0 && map[e.y - 1][e.x] != 1;

    }

    private boolean canMoveNorth(Entity e) {
        return e.y != map.length - 1 && map[e.y + 1][e.x] != 1;

    }
}
