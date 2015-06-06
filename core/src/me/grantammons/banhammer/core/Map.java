package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;

/**
 * Created by grantammons on 5/31/15.
 */
public class Map {
    private int[][] map = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public int[][] getMap() {
        return map;
    }

    public boolean canMove(Entity e, int direction) {
        switch (direction) {
            case Constants.LEFT: return canMoveLeft(e);
            case Constants.RIGHT: return canMoveRight(e);
            case Constants.UP: return canMoveUp(e);
            case Constants.DOWN: return canMoveDown(e);
        }
        return false;
    }

    private boolean canMoveLeft(Entity e) {
        return e.x != 0 && map[e.y][e.x - 1] != 1;

    }

    private boolean canMoveRight(Entity e) {
        return e.x != map[e.y].length - 1 && map[e.y][e.x + 1] != 1;

    }

    private boolean canMoveDown(Entity e) {
        return e.y != 0 && map[e.y - 1][e.x] != 1;

    }

    private boolean canMoveUp(Entity e) {
        return e.y != map.length - 1 && map[e.y + 1][e.x] != 1;

    }
}
