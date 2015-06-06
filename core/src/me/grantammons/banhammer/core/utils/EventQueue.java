package me.grantammons.banhammer.core.utils;

import me.grantammons.banhammer.core.Entity;

import java.util.ArrayList;

/**
 * Created by grantammons on 6/6/15.
 */
public class EventQueue {
    private int time;
    public ArrayList<Entity> entityEvents;
    public ArrayList<Integer> eventTimes;

    public EventQueue() {
        time = 0;
        entityEvents = new ArrayList<Entity>();
        eventTimes = new ArrayList<Integer>();
    }

    public int getTime() {
        return time;
    }

    public void clear() {
        entityEvents.clear();
        eventTimes.clear();
    }

    public void add(Entity entity, int time) {
        int index = entityEvents.size();
        for (int i = 0; i < eventTimes.size(); i++) {
            if (eventTimes.get(i) > time) {
                index = i;
                break;
            }
        }
        entityEvents.add(index, entity);
        eventTimes.add(index, time);

    }

    public Entity get() {
        if (entityEvents.size() == 0)
            return null;

        int _time = eventTimes.remove(0);
        if (_time > 0) {
            time += _time;

            for(int i = 0; i < eventTimes.size(); i++) {
                eventTimes.set(i, eventTimes.get(i) - _time);
            }
        }
        return entityEvents.remove(0);
    }

    private void debugTimes(String s) {
        for(int i = 0; i < eventTimes.size(); i++) {
            System.out.println(s+", eventTimes[" + i + "] to " + eventTimes.get(i));
        }
    }

    public Entity remove(Entity entity) {
        int index = entityEvents.indexOf(entity);
        eventTimes.remove(index);
        return entityEvents.remove(index);
    }

    public ArrayList<Entity> getEntityEvents() {
        return entityEvents;
    }

    public ArrayList<Integer> getEventTimes() {
        return eventTimes;

    }
}
