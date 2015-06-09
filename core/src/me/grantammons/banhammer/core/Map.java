package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;

/**
 * Created by grantammons on 5/31/15.
 */
public class Map {
    public static final int GROUND = 0;
    public static final int BEDROCK = 1;

    public static final int DIRT = 2;
    public static final int DIRTDUG1 = 3;
    public static final int DIRTDUG2 = 4;
    public static final int DIRTDUG3 = 5;
    public static final int GRASS = 6;

    private int[][] map = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 6, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
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
        return intendedTileType(e, direction) != BEDROCK;
    }

    public boolean canDig(Entity e, int direction) {
        int t = intendedTileType(e, direction);
        return (t == DIRT || t == DIRTDUG1 || t == DIRTDUG2 || t == DIRTDUG3);
    }

    public void dig(Entity e, int direction) {
        if (!canDig(e, direction)) return;

        switch (direction) {
            case Constants.WEST: digTile(e.x - 1, e.y); break;
            case Constants.EAST: digTile(e.x + 1, e.y); break;
            case Constants.NORTH: digTile(e.x, e.y + 1); break;
            case Constants.SOUTH: digTile(e.x, e.y - 1); break;
            case Constants.NORTHEAST: digTile(e.x + 1, e.y + 1); break;
            case Constants.NORTHWEST: digTile(e.x - 1, e.y + 1); break;
            case Constants.SOUTHEAST: digTile(e.x + 1, e.y - 1); break;
            case Constants.SOUTHWEST: digTile(e.x - 1, e.y - 1); break;
        }
    }

    private void digTile(int x, int y) {
        switch(map[y][x]) {
            case DIRT:
                map[y][x] = DIRTDUG1;
                break;
            case DIRTDUG1:
                map[y][x] = DIRTDUG2;
                break;
            case DIRTDUG2:
                map[y][x] = DIRTDUG3;
                break;
            case DIRTDUG3:
                map[y][x] = GROUND;
                break;
        }
    }

    private int intendedTileType(Entity e, int direction) {
        switch (direction) {
            case Constants.WEST: return tileWest(e);
            case Constants.EAST: return tileEast(e);
            case Constants.NORTH: return tileNorth(e);
            case Constants.SOUTH: return tileSouth(e);
            case Constants.NORTHEAST:  return tileNorthEast(e);
            case Constants.NORTHWEST: return tileNorthWest(e);
            case Constants.SOUTHEAST: return tileSouthEast(e);
            case Constants.SOUTHWEST: return tileSouthWest(e);
        }
        return BEDROCK;
    }

    private int tileEast(Entity e) {
        if (e.x == map[e.y].length - 1) return BEDROCK;
        return map[e.y][e.x + 1];
    }

    private int tileWest(Entity e) {
        if (e.x == 0) return BEDROCK;
        return map[e.y][e.x - 1];
    }

    private int tileSouth(Entity e) {
        if (e.y == 0) return BEDROCK;
        return map[e.y - 1][e.x];
    }

    private int tileNorth(Entity e) {
        if (e.y == map.length - 1) return BEDROCK;
        return map[e.y + 1][e.x];
    }

    private int tileNorthWest(Entity e) {
        if ((e.y == map.length - 1) || (e.x - 1) < 0)
            return BEDROCK;
        return map[e.y + 1][e.x - 1];
    }
    private int tileNorthEast(Entity e) {
        if ((e.y == map.length - 1) || (e.x == map[e.y].length - 1))
            return BEDROCK;
        return map[e.y + 1][e.x + 1];
    }

    private int tileSouthWest(Entity e) {
        if ((e.y - 1 < 0) || (e.x - 1 < 0))
            return BEDROCK;
        return map[e.y - 1][e.x - 1];
    }
    private int tileSouthEast(Entity e) {
        if ((e.y - 1 < 0) || (e.x == map[e.y].length - 1))
            return BEDROCK;
        return map[e.y - 1][e.x + 1];
    }
}
