package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.banhammer.entities.mobs.Imp;
import me.grantammons.banhammer.entities.playerClasses.Brute;
import me.grantammons.rogueEngine.core.utils.eventing.Scheduler;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by grantammons on 6/6/15.
 */
public class SchedulerTest {
    @Test(description = "adding an item")
    public void testAddingItemToScheduler() {
        Scheduler as = new Scheduler();
        Brute p = new Brute(new Notifier());
        p.name = "player";
        p.setSpeed(10);

        AnimatedEntity e = new Imp(new Notifier());
        e.name = "enemy";
        e.setSpeed(20);

        as.addEntity(p);
        as.addEntity(e);

        Assert.assertEquals(as.nextEntity().name, p.name);
        Assert.assertEquals(as.nextEntity().name, e.name);
        Assert.assertEquals(as.nextEntity().name, p.name);
        Assert.assertEquals(as.nextEntity().name, p.name);
        Assert.assertEquals(as.nextEntity().name, e.name);
        Assert.assertEquals(as.nextEntity().name, p.name);
        Assert.assertEquals(as.nextEntity().name, p.name);
        Assert.assertEquals(as.nextEntity().name, e.name);
    }
}
