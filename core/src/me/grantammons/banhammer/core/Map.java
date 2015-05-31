package me.grantammons.banhammer.core;

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

    public boolean canMoveLeft(Entity e) {
        if (e.x == 0)
            return  false;

        return map[e.y][e.x - 1] != 1;
    }

    public boolean canMoveRight(Entity e) {
        if (e.x == map[e.y].length - 1)
            return  false;

        return map[e.y][e.x + 1] != 1;
    }

    public boolean canMoveDown(Entity e) {
        if (e.y == 0)
            return false;

        return map[e.y - 1][e.x] != 1;
    }

    public boolean canMoveUp(Entity e) {
        if (e.y == map.length - 1)
            return false;

        return map[e.y + 1][e.x] != 1;
    }
}
