package me.grantammons.rogueEngine.core.entities;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.Notifier;

import java.util.ArrayList;

/**
 * Created by grantammons on 6/27/15.
 */
public class Entity {
    public Location location;
    public String name;
    protected boolean isExpired = false;
    protected Notifier notifier;
    protected ArrayList<Location> path;

    public Entity(Notifier notifier) {
        this.notifier = notifier;
        this.path = new ArrayList<>();
    }

    public void setPath(ArrayList<Location> path) {
        // Astar returns the current location as well. remove that.
        path.remove(location);
        this.path = path;
    }

    public int getSpeed() {
        return 0;
    }

    public void takeTurn(Map map) { }

    public boolean hasPath() {
        return !this.path.isEmpty();
    }
}
