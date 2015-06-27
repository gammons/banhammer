package me.grantammons.rogueEngine.core;

import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.core.entities.items.props.Prop;

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
    public static final int WATER = 6;


    private ArrayList<AnimatedEntity> entities;
    private ArrayList<Item> items;
    private ArrayList<Prop> props;
    private int[][] map;

    public Map() {
        entities = new ArrayList<AnimatedEntity>();
        items = new ArrayList<Item>();
        props = new ArrayList<Prop>();
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
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
        return tileAt(location.x, location.y);
    }

    public int tileAt(int x, int y) {
        if ((y < 0 || y >= map.length - 1) ||
                (x < 0 || x >= map[y].length - 1))
            return BEDROCK;
        return map[y][x];
    }

    public boolean anySurrounds(int tileType, Location location) {
        return tileAt(location.x - 1, location.y - 1) == tileType ||
                tileAt(location.x - 1, location.y) == tileType ||
                tileAt(location.x - 1, location.y + 1) == tileType ||
                tileAt(location.x, location.y - 1) == tileType ||
                tileAt(location.x, location.y + 1) == tileType ||
                tileAt(location.x + 1, location.y - 1) == tileType ||
                tileAt(location.x + 1, location.y) == tileType ||
                tileAt(location.x + 1, location.y + 1) == tileType;
    }

    public AnimatedEntity entityAt (Location location) {
        for(AnimatedEntity e : entities) {
            if (e.location.equals(location))
                return e;
        }
        return null;
    }

    public ArrayList<AnimatedEntity> getEntities() {
        return entities;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Prop> getProps() {
        return props;
    }

}
