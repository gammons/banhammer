package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.LevelGen;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.Notifier;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LevelGenTest {
    @Test(description="fills out a map with bedrock")
    public void testLevelGen() {
        Map map = new Map();
        Notifier notifier = new Notifier();
        LevelGen levelGen = new LevelGen(map, notifier);
        Assert.assertEquals(levelGen.stage[0][0], Map.BEDROCK);
    }

    @Test(description="Fills with rooms")
    public void testFillsRooms() {
        Map map = new Map();
        Notifier notifier = new Notifier();
        LevelGen levelGen = new LevelGen(map, notifier);
        levelGen.fillRooms();
        System.out.println(levelGen.stage);

    }
}
