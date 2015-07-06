package me.grantammons.rogueEngine.core.utils.eventing;

import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

import java.util.ArrayList;

/**
 * Created by grantammons on 6/6/15.
 */
public class Scheduler {
    private static final int DEFAULT_DURATION = 1;
    private int duration;
    private EventQueue queue;
    private ArrayList<AnimatedEntity> repeat;
    private AnimatedEntity current;

    public Scheduler() {
        queue = new EventQueue();
        repeat = new ArrayList<AnimatedEntity>();
    }

    public void add(AnimatedEntity e, boolean repeat, int time) {
        if (repeat)
            this.repeat.add(e);
        queue.add(e, time);
    }

    public void addEntity(AnimatedEntity e) {
        repeat.add(e);
        queue.add(e, e.getSpeed());
    }

    public void clear() {
        queue.clear();
        repeat.clear();
        current = null;
        duration = DEFAULT_DURATION;
    }

    public AnimatedEntity remove(AnimatedEntity item) {
        if (item == current)
            duration = DEFAULT_DURATION;

        AnimatedEntity result = queue.remove(item);
        repeat.remove(item);

        if (current == item)
            current = null;

        return result;
    }

    public AnimatedEntity nextEntity() {
        if (current != null && repeat.indexOf(current) != -1) {
            queue.add(current, current.getSpeed());
        }
        current = queue.get();
        duration = current.getSpeed();

        return current;
    }

    public AnimatedEntity currentEntity() {
        return current;
    }

    public void setDuration(int time) {
        if (current != null)
            duration = time;
    }

    public int getTime() { return queue.getTime(); }
}
