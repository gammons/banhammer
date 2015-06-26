package me.grantammons.rogueEngine.core;

import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.rogueEngine.core.items.Item;

import java.util.ArrayList;

/**
 * Created by grantammons on 5/31/15.
 */
public class Map {
    public static final int BEDROCK = 0;
    public static final int GROUND = 1;
    public static final int DIRT = 2;
    public static final int DIRTDUG1 = 3;
    public static final int DIRTDUG2 = 4;
    public static final int DIRTDUG3 = 5;

    private int[][] map = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public ArrayList<Entity> entities;
    public ArrayList<Item> items;

    public Map() {
        entities = new ArrayList<Entity>();
        items = new ArrayList<Item>();
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

    public int tileAt(Location location) {
        if ((location.y < 0 || location.y >= map.length - 1) ||
                (location.x < 0 || location.x >= map[location.y].length - 1))
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
