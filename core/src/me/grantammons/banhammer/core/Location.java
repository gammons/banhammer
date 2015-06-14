package me.grantammons.banhammer.core;

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

    public boolean equals(Location other) {
        return (other.x == x && other.y == y);
    }
}
