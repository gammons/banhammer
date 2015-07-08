package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.banhammer.entities.playerClasses.Brute;
import me.grantammons.rogueEngine.core.utils.eventing.EventQueue;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by grantammons on 6/6/15.
 */
public class EventQueueTest {

    @Test(description="Default time is zero")
    public void testGetTimeIsZero() {
        EventQueue eq = new EventQueue();
        Assert.assertEquals(eq.getTime(), 0);
    }

    @Test(description="adding an event to the queue does not change getTime")
    public void testAddItemChangesTime() {
        EventQueue eq = new EventQueue();
        eq.add(new Brute(new Notifier()), 10);
        Assert.assertEquals(eq.getTime(), 0);
    }

    @Test(description="adding an event adds to entityEvents and eventTimes")
    public void testAddItemAddsToEntityEvents() {
        EventQueue eq = new EventQueue();
        Brute p = new Brute(new Notifier());
        eq.add(p, 10);
        Assert.assertEquals(eq.getEntityEvents().size(), 1);
        Assert.assertEquals(eq.getEntityEvents().get(0), p);
    }

    @Test(description="getting an event changes getTime")
    public void testGetItem() {
        EventQueue eq = new EventQueue();
        Brute p = new Brute(new Notifier());
        eq.add(p, 10);
        Entity e = eq.get();
        Assert.assertEquals(e, p);
        Assert.assertEquals(eq.getTime(), 10);
    }

    @Test(description="When there are multiple events of different lengths, it will return the shortest length event first")
    public void testAddEvents() {
        System.out.println("My test");
        EventQueue eq = new EventQueue();
        Brute p1 = new Brute(new Notifier());
        Brute p2 = new Brute(new Notifier());
        Brute p3 = new Brute(new Notifier());

        eq.add(p1, 5);
        eq.add(p2, 10);
        eq.add(p3, 1);

        Entity e = eq.get();
        Assert.assertEquals(e, p3);
        Assert.assertEquals(eq.getTime(), 1);

        e = eq.get();
        Assert.assertEquals(e, p1);
        Assert.assertEquals(eq.getTime(), 5);

        e = eq.get();
        Assert.assertEquals(e, p2);
        Assert.assertEquals(eq.getTime(), 10);
    }

    @Test(description="Remove an event")
    public void testRemoveEvent() {
        EventQueue eq = new EventQueue();
        Brute p1 = new Brute(new Notifier());
        Brute p2 = new Brute(new Notifier());
        Brute p3 = new Brute(new Notifier());

        eq.add(p1, 1);
        eq.add(p2, 2);
        eq.add(p3, 3);

        eq.remove(p2);

        Assert.assertEquals(eq.getEntityEvents().get(0), p1);
        Assert.assertEquals(eq.getEntityEvents().get(1), p3);

        Assert.assertEquals(eq.getEventTimes().get(0), new Integer(1));
        Assert.assertEquals(eq.getEventTimes().get(1), new Integer(3));
    }
}