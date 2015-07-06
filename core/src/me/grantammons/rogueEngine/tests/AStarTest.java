package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.utils.pathfinding.AStar;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AStarTest {
    private Map map;

    private int[][] generatedMap = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };


    @Test(description="Test AStar")
    public void testAStar() {
        map = new Map();
        map.setMap(generatedMap);
        AStar AStar = new AStar(new Location(2, 5), map);
        for (Location step : AStar.compute(new Location(1,4))) {
            System.out.println(step);
        }
    }
    @Test(description="It does not prefer south")
    public void testAStarDoesNotPreferSouth() {
        map = new Map();
        map.setMap(generatedMap);
        AStar AStar = new AStar(new Location(5, 6), map);
        int i = 7;
        for (Location step : AStar.compute(new Location(7,6))) {
            Assert.assertEquals(step, new Location(i,6));
            i--;
        }
    }
}
