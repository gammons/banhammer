package me.grantammons.banhammer.core.utils;

import me.grantammons.banhammer.core.Entity;

import java.util.ArrayList;

/**
 * Created by grantammons on 6/6/15.
 */
public class Scheduler {
    private EventQueue queue;
    private ArrayList<Entity> repeat;
    private Entity current;

    public Scheduler() {
        queue = new EventQueue();
        repeat = new ArrayList<Entity>();
    }

    public int getTime() {
        return queue.getTime();
    }

    public void add(Entity e, boolean repeat) {
        if (repeat)
            this.repeat.add(e);
    }

    public void clear() {
        queue.clear();
        repeat.clear();
        current = null;
    }

    public Entity remove(Entity item) {
        Entity result = queue.remove(item);
        repeat.remove(item);
        if (current == item)
            current = null;

        return result;
    }

    public Entity next() {
        current = queue.get();
        return current;
    }
}
