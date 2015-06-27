package me.grantammons.rogueEngine.core.entities;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Notifier;

/**
 * Created by grantammons on 6/27/15.
 */
public class Entity {
    public Location location;
    public String name;
    protected boolean isExpired = false;
    protected Notifier notifier;

    public Entity(Notifier notifier) {
        this.notifier = notifier;
    }
}
