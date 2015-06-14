package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;

import java.util.ArrayList;

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
        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 2, 1, 0, 0, 1},
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

    public ArrayList<Entity> entities;

    public Map() {
        entities = new ArrayList<Entity>();
    }

    public int[][] getMap() {
        return map;
    }

    public boolean canMove(Location location) {
        return tileAt(location) != BEDROCK;
    }

    public boolean canDig(Location location) {
        int t = tileAt(location);
        return (t == DIRT || t == DIRTDUG1 || t == DIRTDUG2 || t == DIRTDUG3);
    }

    public void dig(Location location) {
        switch(map[location.y][location.x]) {
            case DIRT:
                map[location.y][location.x] = DIRTDUG1;
                break;
            case DIRTDUG1:
                map[location.y][location.x] = DIRTDUG2;
                break;
            case DIRTDUG2:
                map[location.y][location.x] = DIRTDUG3;
                break;
            case DIRTDUG3:
                map[location.y][location.x] = GROUND;
                break;
        }
    }

    private int tileAt(Location location) {
        if ((location.x <= 0 || location.x >= map[location.y].length) ||
                (location.y <= 0 || location.y >= map.length))
            return BEDROCK;
        return map[location.y][location.x];
    }

    public Entity entityAt (Location location) {
        for(Entity e : entities) {
            if (e.location.equals(location))
                return e;
        }
        return null;
    }
}
