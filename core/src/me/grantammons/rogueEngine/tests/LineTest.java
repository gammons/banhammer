package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.utils.los.Line;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LineTest {

    @Test(description="Test horizontal lines")
    public void TestHorizontalLine() {
        Line l = new Line(new Map());
        ArrayList<Location> line;
        line = l.getLine(new Location(10,5), new Location(12,5));
        Assert.assertEquals(line.get(0), new Location(10,5));
        Assert.assertEquals(line.get(1), new Location(11,5));
        Assert.assertEquals(line.get(2), new Location(12,5));
    }

    @Test(description="Test vertical lines")
    public void TestVerticalLine() {
        Line l = new Line(new Map());
        ArrayList<Location> line;
        line = l.getLine(new Location(10,5), new Location(10,7));
        Assert.assertEquals(line.get(0), new Location(10,5));
        Assert.assertEquals(line.get(1), new Location(10,6));
        Assert.assertEquals(line.get(2), new Location(10,7));
    }

    @Test(description="Test diagonal lines")
    public void TestDiagonallLine() {
        Line l = new Line(new Map());
        ArrayList<Location> line;
        line = l.getLine(new Location(10,5), new Location(12,7));
        Assert.assertEquals(line.get(0), new Location(10,5));
        Assert.assertEquals(line.get(1), new Location(11,6));
        Assert.assertEquals(line.get(2), new Location(12,7));
    }

    @Test(description="Test diagonal lines")
    public void TestDiagonallLineBottomLeft() {
        Line l = new Line(new Map());
        ArrayList<Location> line;
        line = l.getLine(new Location(10,5), new Location(8,3));
        Assert.assertEquals(line.get(0), new Location(8,3));
        Assert.assertEquals(line.get(1), new Location(9,4));
        Assert.assertEquals(line.get(2), new Location(10,5));
    }
}
