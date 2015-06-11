package me.grantammons.banhammer.tests;

import me.grantammons.banhammer.core.entities.Entity;
import me.grantammons.banhammer.core.entities.Player;
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
        Player p = new Player();
        p.setSpeed(10);
        p.setName("player");

        Entity e = new Entity();
        e.setSpeed(20);
        e.setName("enemy");

        as.addEntity(p);
        as.addEntity(e);

        Assert.assertEquals(as.nextEntity().getName(), p.getName());
        Assert.assertEquals(as.nextEntity().getName(), e.getName());
        Assert.assertEquals(as.nextEntity().getName(), p.getName());
        Assert.assertEquals(as.nextEntity().getName(), p.getName());
        Assert.assertEquals(as.nextEntity().getName(), e.getName());
        Assert.assertEquals(as.nextEntity().getName(), p.getName());
        Assert.assertEquals(as.nextEntity().getName(), p.getName());
        Assert.assertEquals(as.nextEntity().getName(), e.getName());
    }
}
