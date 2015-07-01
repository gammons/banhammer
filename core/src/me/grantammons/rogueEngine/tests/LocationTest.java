package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.Location;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by grantammons on 7/1/15.
 */
public class LocationTest {
    @Test(description = "location equality")
    public void testLocationEquality() {
        Location l1 = new Location(5,6);
        Location l2 = new Location(5,6);
        Location l3 = new Location(6,6);

        Assert.assertEquals(true, l1.equals(l2));
        Assert.assertEquals(false, l1.equals(l3));
    }

    @Test(description = "locations in an arraylist")
    public void testLocationsArrayList() {
        Location l1 = new Location(5,6);
        Location l2 = new Location(6,6);

        ArrayList<Location> locations = new ArrayList<>();
        locations.add(l1);
        locations.add(l2);

        Assert.assertEquals(true, locations.contains(new Location(5,6)));

    }
}
