package me.grantammons.banhammer.core

import me.grantammons.banhammer.core.entities.Entity

class Map {
    static final int GROUND = 0
    static final int BEDROCK = 1

    static final int DIRT = 2
    static final int DIRTDUG1 = 3
    static final int DIRTDUG2 = 4
    static final int DIRTDUG3 = 5
    static final int GRASS = 6

    int[][] map = [
        [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
        [1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 6, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1],
        [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    ]

    boolean canMove(Entity e, int direction) {
        intendedTileType(e, direction) != BEDROCK
    }

    boolean canDig(Entity e, int direction) {
        int t = intendedTileType(e, direction)
        (t == DIRT || t == DIRTDUG1 || t == DIRTDUG2 || t == DIRTDUG3)
    }

    def dig(Entity e, int direction) {
        switch (direction) {
            case Constants.WEST: digTile(e.getX() - 1, e.getY()); break
            case Constants.EAST: digTile(e.getX() + 1, e.getY()); break
            case Constants.NORTH: digTile(e.getX(), e.getY() + 1); break
            case Constants.SOUTH: digTile(e.getX(), e.getY() - 1); break
            case Constants.NORTHEAST: digTile(e.getX() + 1, e.getY() + 1); break
            case Constants.NORTHWEST: digTile(e.getX() - 1, e.getY() + 1); break
            case Constants.SOUTHEAST: digTile(e.getX() + 1, e.getY() - 1); break
            case Constants.SOUTHWEST: digTile(e.getX() - 1, e.getY() - 1); break
        }
    }

    private digTile(int x, int y) {
        switch(map[y][x]) {
            case DIRT:
                map[y][x] = DIRTDUG1
                break
            case DIRTDUG1:
                map[y][x] = DIRTDUG2
                break
            case DIRTDUG2:
                map[y][x] = DIRTDUG3
                break
            case DIRTDUG3:
                map[y][x] = GROUND
                break
        }
    }

    private int intendedTileType(Entity e, int direction) {
        switch (direction) {
            case Constants.WEST: return tileWest(e)
            case Constants.EAST: return tileEast(e)
            case Constants.NORTH: return tileNorth(e)
            case Constants.SOUTH: return tileSouth(e)
            case Constants.NORTHEAST:  return tileNorthEast(e)
            case Constants.NORTHWEST: return tileNorthWest(e)
            case Constants.SOUTHEAST: return tileSouthEast(e)
            case Constants.SOUTHWEST: return tileSouthWest(e)
        }
        return BEDROCK;
    }

    private int tileEast(Entity e) {
        if (e.getX() == map[e.getY()].length - 1) return BEDROCK
        return map[e.getY()][e.getX() + 1]
    }

    private int tileWest(Entity e) {
        if (e.getX() == 0) return BEDROCK
        return map[e.getY()][e.getX() - 1]
    }

    private int tileSouth(Entity e) {
        if (e.getY() == 0) return BEDROCK
        return map[e.getY() - 1][e.getX()]
    }

    private int tileNorth(Entity e) {
        if (e.getY() == map.length - 1) return BEDROCK
        return map[e.getY() + 1][e.getX()]
    }

    private int tileNorthWest(Entity e) {
        if ((e.getY() == map.length - 1) || (e.getX() - 1) < 0)
            return BEDROCK
        return map[e.getY() + 1][e.getX() - 1]
    }
    private int tileNorthEast(Entity e) {
        if ((e.getY() == map.length - 1) || (e.getX() == map[e.getY()].length - 1))
            return BEDROCK
        return map[e.getY() + 1][e.getX() + 1]
    }

    private int tileSouthWest(Entity e) {
        if ((e.getY() - 1 < 0) || (e.getX() - 1 < 0))
            return BEDROCK
        return map[e.getY() - 1][e.getX() - 1]
    }
    private int tileSouthEast(Entity e) {
        if ((e.getY() - 1 < 0) || (e.getX() == map[e.getY()].length - 1))
            return BEDROCK
        return map[e.getY() - 1][e.getX() + 1]
    }
}
