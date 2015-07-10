package me.grantammons.rogueEngine.core.fov;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import rlforj.los.IFovAlgorithm;
import rlforj.los.ILosBoard;
import rlforj.los.PrecisePermissive;

import java.util.ArrayList;

public class Fov implements ILosBoard {
    private int[][] map;
    private ArrayList<Location> visibleTiles;

    public Fov(int[][] map) {
        this.map = map;
        visibleTiles = new ArrayList<>();
    }

    public void calculateFov(Location location, int radius) {
        IFovAlgorithm p = new PrecisePermissive();
        p.visitFieldOfView(this, location.x, location.y, 2);
    }

    @Override
    public boolean contains(int x, int y) {
        return (x > 0 && y > 0) && (y < map.length) && (x < map[y].length);
    }

    @Override
    public boolean isObstacle(int x, int y) {
        return (contains(x,y) && (map[y][x] != Map.GROUND));
    }

    @Override
    public void visit(int x, int y) {
        visibleTiles.add(new Location(x,y));
    }

    public void resetFOV() {
        visibleTiles.clear();
    }

    public ArrayList<Location> getVisibleTiles() {
        return visibleTiles;
    }
}
