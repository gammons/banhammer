package me.grantammons.rogueEngine.core;

/**
 * Created by grantammons on 6/12/15.
 */
public class Location {
    public int x;
    public int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Location setLocationFromDirection(Location location, int direction) {
        Location loc = new Location(location.x, location.y);
        switch (direction) {
            case Constants.WEST: loc.x--; break;
            case Constants.EAST: loc.x++; break;
            case Constants.NORTH: loc.y++; break;
            case Constants.SOUTH: loc.y--; break;
            case Constants.NORTHEAST:
                loc.y++;
                loc.x++;
                break;
            case Constants.NORTHWEST:
                loc.y++;
                loc.x--;
                break;
            case Constants.SOUTHEAST:
                loc.y--;
                loc.x++;
                break;
            case Constants.SOUTHWEST:
                loc.y--;
                loc.x--;
                break;
        }
        return loc;
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Location) {
            Location l = (Location)other;
            return (l.x == x && l.y == y);
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";

    }
}
