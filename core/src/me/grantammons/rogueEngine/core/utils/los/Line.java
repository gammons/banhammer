package me.grantammons.rogueEngine.core.utils.los;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;

import java.util.ArrayList;

public class Line {
    private Map map;

    public Line(Map map) {
        this.map = map;
    }

    public ArrayList<Location> getLine(Location in1, Location in2) {
        Location l1 = new Location(in1.x, in1.y);
        Location l2 = new Location(in2.x, in2.y);

        ArrayList<Location> points = new ArrayList<>();

        boolean steep = Math.abs(l2.y - l1.y) > Math.abs(l2.x - l1.x);

        if (steep) swapY(l1, l2);
        if (l1.x > l2.x) swap(l1, l2);

        int deltaX = l2.x - l1.x;
        int deltaY = Math.abs(l2.y - l1.y);

        int error = (int)(deltaX / 2);
        int y = l1.y;
        int ystep;

        if (l1.y < l2.y) {
            ystep = 1;
        } else {
            ystep = -1;
        }

        for (int x = l1.x; x <= l2.x; x++ ) {
            if (steep) {
                 points.add(new Location(y, x));
            } else {
                points.add(new Location(x,y));
            }
            error -= deltaY;
            if (error < 0) {
                y += ystep;
                error += deltaX;
            }
        }

        return points;

    }

    public boolean hasClearLine(Location l1, Location l2) {
        for (Location l : getLine(l1, l2)) {
            if (l.equals(l1) || l.equals(l2)) continue;
            if (map.entityOrWallAt(l)) return false;
        }
        return true;
        //return !getLine(l1, l2).anyMatch(map::entityOrWallAt);
    }

    private void swapY(Location l1, Location l2) {
        int t = l1.x;
        l1.x = l1.y;
        l1.y = t;

        t = l2.x;
        l2.x = l2.y;
        l2.y = t;
    }

    private void swap(Location l1, Location l2) {
        int t = l1.x;
        l1.x = l2.x;
        l2.x = t;

        t = l1.y;
        l1.y = l2.y;
        l2.y = t;
    }
}
