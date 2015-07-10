package me.grantammons.rogueEngine.core.entities;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.Notifier;

import java.util.ArrayList;

public class Entity {
    public Location location;
    public Location intendedLocation;
    public String name;

    public boolean isExpired() {
        return isExpired;
    }

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
