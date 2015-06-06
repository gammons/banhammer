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
        p.speed = 1;
        p.name = "player";

        Entity e = new Entity();
        e.speed = 2;
        e.name = "enemy";

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
