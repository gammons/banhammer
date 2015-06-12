package me.grantammons.banhammer.core.utils
import me.grantammons.banhammer.core.entities.Entity

class Scheduler {
    private static final int DEFAULT_DURATION = 1
    private int duration
    private EventQueue queue
    private ArrayList<Entity> repeat
    private Entity current

    Scheduler() {
        queue = new EventQueue()
        repeat = new ArrayList<Entity>()
    }

    def add(Entity e, boolean repeat, int time) {
        if (repeat)
            this.repeat.add(e)
        queue.add(e, time)
    }

    def addEntity(Entity e) {
        repeat.add(e);
        queue.add(e, e.getSpeed())
    }

    def clear() {
        queue.clear()
        repeat.clear()
        current = null;
        duration = DEFAULT_DURATION
    }

    Entity remove(Entity item) {
        if (item == current) duration = DEFAULT_DURATION;

        Entity result = queue.remove(item)
        repeat.remove(item)

        if (current == item)
            current = null

        result;
    }

    Entity nextEntity() {
        if (current != null && repeat.indexOf(current) != -1) {
            queue.add(current, current.getSpeed())
        }
        current = queue.get()
        duration = current.getSpeed()

        current
    }

    Entity currentEntity() { current; }

    def setDuration(int time) {
        if (current != null)
            duration = time
    }

    int getTime() { return queue.getTime() }
}
