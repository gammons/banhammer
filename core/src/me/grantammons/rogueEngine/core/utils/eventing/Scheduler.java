package me.grantammons.rogueEngine.core.utils.eventing;

import me.grantammons.rogueEngine.core.entities.Entity;

import java.util.ArrayList;

/**
 * Created by grantammons on 6/6/15.
 */
public class Scheduler {
    private static final int DEFAULT_DURATION = 1;
    private int duration;
    private EventQueue queue;
    private ArrayList<Entity> repeat;
    private Entity current;

    public Scheduler() {
        queue = new EventQueue();
        repeat = new ArrayList<>();
    }

    public void add(Entity e, boolean repeat, int time) {
        if (repeat)
            this.repeat.add(e);
        queue.add(e, time);
    }

    public void addEntity(Entity e) {
        repeat.add(e);
        queue.add(e, e.getSpeed());
    }

    public void clear() {
        queue.clear();
        repeat.clear();
        current = null;
        duration = DEFAULT_DURATION;
    }

    public Entity remove(Entity item) {
        if (item == current)
            duration = DEFAULT_DURATION;

        Entity result = queue.remove(item);
        repeat.remove(item);

        if (current == item)
            current = null;

        return result;
    }

    public Entity nextEntity() {
        if (current != null && repeat.indexOf(current) != -1) {
            queue.add(current, current.getSpeed());
        }
        current = queue.get();
        duration = current.getSpeed();

        return current;
    }

    public Entity currentEntity() {
        return current;
    }

    public void setDuration(int time) {
        if (current != null)
            duration = time;
    }

    public int getTime() { return queue.getTime(); }
}
