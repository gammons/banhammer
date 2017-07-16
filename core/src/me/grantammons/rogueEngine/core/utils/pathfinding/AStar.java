package me.grantammons.rogueEngine.core.utils.pathfinding;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;

import java.util.ArrayList;

public class AStar {
    private final Map map;
    private Location to;
    private Location from;
    private ArrayList<AStarLocation> todo;
    private ArrayList<AStarLocation> done;


    public AStar(Location to, Map map) {
        this.todo = new ArrayList<>();
        this.done = new ArrayList<>();
        this.to = to;
        this.map = map;
    }

    public ArrayList<Location> compute(Location from) {
        this.from = from;
        add(to, null);
        populateTodo(from);
        return calculatePath(from);
    }

    private ArrayList<Location> calculatePath(Location from) {
        ArrayList<Location> path = new ArrayList<>();
        AStarLocation loc = null;

        for(AStarLocation l : done) {
            if (l.location.equals(from)) {
                loc = l;
                break;
            }
        }

        while(loc != null) {
            path.add(loc.location);
            loc = loc.prev;
        }
        return path;
    }

    private void populateTodo(Location from) {
        AStarLocation item;
        while(todo.size() > 0) {
            item = todo.get(0);
            todo.remove(item);

            if (item.location.equals(from)) break;

            for (Location loc : map.getPassableNeighbors(item.location)) {
                if (containsLocation(loc)) continue;
                add(loc, item);
            }
        }
    }

    private boolean containsLocation(Location loc) {
        boolean doneContainsLocation = false;
        for (AStarLocation aStarLocation : done) {
            if (aStarLocation.location.equals(loc))
                doneContainsLocation = true;
        }
        return doneContainsLocation;
    }

    private void add(Location l , AStarLocation prev) {
        AStarLocation v = new AStarLocation(l, prev);
        done.add(v);

        int f = v.id + v.distance;
        int i = 0;

        for (AStarLocation loc : todo) {
            if (f < loc.id + loc.distance) {
                todo.add(i, v);
                return;
            }
            i++;
        }
        todo.add(v);
    }

    private class AStarLocation {
        public Location location;
        public AStarLocation prev;
        public int distance;
        public int id;

        public AStarLocation(Location location, AStarLocation prev) {
            this.location = location;
            this.prev = prev;
            this.distance = distance(location);
            this.id = ((prev == null) ? 0 : prev.id + 1);
        }

        private int distance(Location l) {
            return Math.max(Math.abs(l.x - from.x), Math.abs(l.y - from.y));
        }
    }
}
