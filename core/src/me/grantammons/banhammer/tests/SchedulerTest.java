package me.grantammons.banhammer.tests;

import me.grantammons.banhammer.core.Notifier;
import me.grantammons.banhammer.core.entities.Entity;
import me.grantammons.banhammer.core.entities.mobs.Imp;
import me.grantammons.banhammer.core.entities.playerClasses.Brute;
import me.grantammons.banhammer.core.utils.Scheduler;
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

        Entity e = new Imp(new Notifier());
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
