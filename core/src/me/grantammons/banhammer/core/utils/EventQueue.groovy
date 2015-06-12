package me.grantammons.banhammer.core.utils
import me.grantammons.banhammer.core.entities.Entity

class EventQueue {
    int time
    ArrayList<Entity> entityEvents
    ArrayList<Integer> eventTimes

    EventQueue() {
        time = 0
        entityEvents = new ArrayList<Entity>()
        eventTimes = new ArrayList<Integer>()
    }

    def clear() {
        entityEvents.clear()
        eventTimes.clear()
    }

    def add(Entity entity, int time) {
        int index = entityEvents.size()

        for (int i = 0; i < eventTimes.size(); i++) {
            if (eventTimes.get(i) > time) {
                index = i
                break
            }
        }
        entityEvents.add(index, entity)
        eventTimes.add(index, time)

    }

    Entity get() {
        if (entityEvents.size() == 0)
            return null

        int _time = eventTimes.remove(0)
        if (_time > 0) {
            time += _time

            for(int i = 0; i < eventTimes.size(); i++) {
                eventTimes.set(i, eventTimes.get(i) - _time)
            }
        }
        entityEvents.remove(0)
    }

    Entity remove(Entity entity) {
        int index = entityEvents.indexOf(entity)
        eventTimes.remove(index)
        entityEvents.remove(index)
    }

    def debugEvents(String s) {
        System.out.print(s + " [")
        for(Entity e : entityEvents) {
            System.out.print(e.getName() + ", ")
        }
        System.out.print("]\n")
    }

    def debugTimes(String s) {
        for(int i = 0; i < eventTimes.size(); i++) {
            System.out.println(s+", eventTimes[" + i + "] to " + eventTimes.get(i))
        }
    }

}
