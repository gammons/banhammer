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
        return e.x != 0 && map[e.y][e.x - 1] != 1;

    }

    public boolean canMoveRight(Entity e) {
        return e.x != map[e.y].length - 1 && map[e.y][e.x + 1] != 1;

    }

    public boolean canMoveDown(Entity e) {
        return e.y != 0 && map[e.y - 1][e.x] != 1;

    }

    public boolean canMoveUp(Entity e) {
        return e.y != map.length - 1 && map[e.y + 1][e.x] != 1;

    }
}
