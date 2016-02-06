package me.grantammons.rogueEngine.core;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class LevelGen {
    private static final int STAGE_WIDTH = 33;
    private static final int STAGE_HEIGHT = 33;

    private static final int NUM_ROOM_TRIES = 12;
    private static final int ROOM_EXTRA_SIZE = 1;

    private ArrayList<Room> rooms;

    public Map map;
    private Level level;
    public int[][] stage;


    public LevelGen(Map map, Notifier notifier) {
        this.map = map;
        level = new Level(map, notifier);
        stage = new int[STAGE_WIDTH][STAGE_HEIGHT];
        rooms = new ArrayList<>();
    }

    private void findPlayerSpawn() {
        boolean found = false;

        int x = 0;
        int y = 0;

        while(!found) {
            x = MathUtils.random(0, STAGE_WIDTH - 1);
            y = MathUtils.random(0, STAGE_HEIGHT - 1);
            if (stage[y][x] == Map.GROUND) found = true;
        }
        level.setPlayerSpawnLocation(new Location(x,y));
    }

    /*
    Growing tree algorithm - http://www.astrolog.org/labyrnth/algrithm.htm
    http://weblog.jamisbuck.org/2011/1/27/maze-generation-growing-tree-algorithm
    This is a general algorithm, capable of creating Mazes of different textures.
    It requires storage up to the size of the Maze.
    Each time you carve a cell, add that cell to a list.
    Proceed by picking a cell from the list, and carving into an unmade cell next to it.
    If there are no unmade cells next to the current cell, remove the current cell from the list.
    The Maze is done when the list becomes empty.
    The interesting part that allows many possible textures is how you pick a cell from the list.
    For example, if you always pick the most recent cell added to it, this algorithm turns into the recursive backtracker.
    If you always pick cells at random, this will behave similarly but not exactly to Prim's algorithm.
    If you always pick the oldest cells added to the list, this will create Mazes with about as low a "river" factor as possible, even lower than Prim's algorithm.
    If you usually pick the most recent cell, but occasionally pick a random cell, the Maze will have a high "river" factor but a short direct solution.
    If you randomly pick among the most recent cells, the Maze will have a low "river" factor but a long windy solution.
     */
    public Level generate() {
        fillStage();
        fillRooms();
        addMaze();
        connectRooms();
        findPlayerSpawn();
        level.setStage(stage);
        return level;
    }

    private void addMaze() {
        ArrayList<Location> cells = new ArrayList<>();
        ArrayList<Location> visited = new ArrayList<>();

        cells.add(findMazeStartPoint());

        while(!cells.isEmpty()) {
            Location l = cells.get(cells.size() - 1);

            if (allNeighborsVisited(l, visited))
                cells.remove(l);

            carveNeighbor(l, cells, visited);
        }
    }

    private boolean allNeighborsVisited(Location loc, ArrayList<Location> visited) {
        Location neighborNorth = new Location(loc.x, loc.y + 2);
        Location neighborSouth = new Location(loc.x, loc.y - 2);
        Location neighborEast = new Location(loc.x + 2, loc.y);
        Location neighborWest = new Location(loc.x - 2, loc.y);

        boolean northVisited = false;
        boolean eastVisited = false;
        boolean westVisited = false;
        boolean southVisited = false;

        if (isNotWithinMaze(neighborNorth) || isInRoom(neighborNorth) || visited.stream().anyMatch(l -> l.equals(neighborNorth)))
            northVisited = true;
        if (isNotWithinMaze(neighborSouth) || isInRoom(neighborSouth) || visited.stream().anyMatch(l -> l.equals(neighborSouth)))
            southVisited = true;
        if (isNotWithinMaze(neighborEast) || isInRoom(neighborEast) || visited.stream().anyMatch(l -> l.equals(neighborEast)))
            eastVisited = true;
        if (isNotWithinMaze(neighborWest) || isInRoom(neighborWest) || visited.stream().anyMatch(l -> l.equals(neighborWest)))
            westVisited = true;

        return (northVisited && southVisited && eastVisited && westVisited);
    }

    private void carveNeighbor(Location l, ArrayList cells, ArrayList visited) {
        Location loc;
        switch(MathUtils.random(0,3)) {
            case Constants.NORTH:
                loc = new Location(l.x, l.y + 2);
                if (isWithinMaze(loc) && !isVisited(loc, visited) && !isInRoom(loc)) {
                    growMaze(loc, new Location(l.x, l.y + 1), cells, visited);
                }
                break;
            case Constants.SOUTH:
                loc = new Location(l.x, l.y - 2);
                if (isWithinMaze(loc) && !isVisited(loc, visited) && !isInRoom(loc)) {
                    growMaze(loc, new Location(l.x, l.y - 1), cells, visited);
                }
                break;
            case Constants.EAST:
                loc = new Location(l.x + 2, l.y);
                if (isWithinMaze(loc) && !isVisited(loc, visited) && !isInRoom(loc)) {
                    growMaze(loc, new Location(l.x + 1, l.y), cells, visited);
                }
                break;
            case Constants.WEST:
                loc = new Location(l.x - 2, l.y);
                if (isWithinMaze(loc) && !isVisited(loc, visited) && !isInRoom(loc)) {
                    growMaze(loc, new Location(l.x - 1, l.y), cells, visited);
                }
                break;
        }
    }

    private boolean isInRoom(Location loc) {
        return rooms.stream().anyMatch(r -> r.contains(loc));
    }

    private void growMaze(Location loc, Location loc2, ArrayList cells, ArrayList visited) {
        carve(loc);
        carve(loc2);
        cells.add(loc);
        visited.add(loc);
    }

    private boolean isNotWithinMaze(Location loc) {
        return loc.x  < 0 || loc.x >= STAGE_WIDTH || loc.y < 0 || loc.y >= STAGE_HEIGHT;
    }

    private boolean isWithinMaze(Location loc) {
        return !isNotWithinMaze(loc);
    }

    private boolean isVisited(Location location, ArrayList visited) {
        return visited.stream().anyMatch(l -> l.equals(location));
    }

    private Location findMazeStartPoint() {
        Location loc = null;
        int x;
        int y;

        while (loc == null) {
            x = MathUtils.random(0, STAGE_WIDTH - 2);
            y = MathUtils.random(0, STAGE_WIDTH - 2);
            if (x % 2 == 0) x++;
            if (y % 2 == 0) y++;

            if (stage[y][x] == Map.BEDROCK) {
                loc = new Location(x, y);
            }
        }
        return loc;
    }

    private void carve(Location l) {
        stage[l.y][l.x] = Map.GROUND;
    }

    private void fillStage() {
        for(int y = 0; y < STAGE_HEIGHT; y++) {
            for(int x = 0; x < STAGE_WIDTH; x++) {
                stage[y][x] = Map.BEDROCK;
            }
        }
    }

    public void fillRooms() {
        for(int i = 0; i < NUM_ROOM_TRIES; i++) {
            Room r = new Room();

            boolean overlaps = false;
            if (rooms.stream().anyMatch(other -> r.intersects(other))) overlaps = true;
            if (!overlaps) rooms.add(r);
        }

        for (Room room : rooms) {
            for(int y = room.y; y < room.y + room.height; y++) {
                for(int x = room.x; x < room.x + room.width; x++) {
                    stage[y][x] = Map.GROUND;
                }
            }
        }
    }

    class Room {
        public int x;
        public int y;
        public int width;
        public int height;

        public Room() {
            int size = (int) (MathUtils.randomTriangular(1, 3 + ROOM_EXTRA_SIZE) * 2 + 1);
            if (size % 2 == 0) size++; //ensure we have an odd-sized room
            int rectangularity = (int) (MathUtils.randomTriangular(0, 1 + size / 2) * 2);
            if (rectangularity % 2 != 0) rectangularity++;

            width = size;
            height = size;

            if (MathUtils.randomBoolean()) {
                width += rectangularity;
            } else {
                height += rectangularity;
            }

            x = MathUtils.random(0, STAGE_WIDTH - width - 1);
            y = MathUtils.random(0, STAGE_HEIGHT - height - 1);
            if (x % 2 == 0) x++;
            if (y % 2 == 0) y++;
        }

        public String toString() {
            return "Room at (" + x + ", " + y + ") with width "+ width + " and height " + height;
        }

        public boolean intersects(Room other) {
            return Intersector.intersectRectangles(getRectangle(), other.getRectangle(), new Rectangle());
        }

        public Rectangle getRectangle() {
            return new Rectangle(this.x, this.y, this.width, this.height);
        }

        public boolean contains(Location loc) {
            return ((loc.x >= this.x) && (loc.x <= this.width + this.x) && (loc.y >= this.y) && (loc.y <= this.height + this.y));
        }

        public Location getRandomPointForDoorway() {
            ArrayList<Location> points = new ArrayList<>();
            for(int y = this.y; y <= this.y + this.height; y++) {
                Location l1 = new Location(this.x - 1, y);
                Location l2 = new Location(this.x + this.width, y);
                if (isWithinMaze(l1)) points.add(l1);
                if (isWithinMaze(l2)) points.add(l2);
            }

            for (int x = this.x; x <= this.x + this.width; x++) {
                Location l1 = new Location(x, this.y - 1);
                Location l2 = new Location(x, this.y + this.height);
                if (isWithinMaze(l1)) points.add(l1);
                if (isWithinMaze(l2)) points.add(l2);
            }
            return points.get(MathUtils.random(0, points.size() - 1));
        }
    }

    private void connectRooms() {
        for (Room room : rooms) {
            int doors = MathUtils.random(1,3);
            int implementedDoors = 0;
            while (implementedDoors < doors) {
                Location point = room.getRandomPointForDoorway();
                Location above = new Location(point.x, point.y - 1);
                Location below = new Location(point.x, point.y + 1);
                Location leftSide = new Location(point.x - 1, point.y);
                Location rightSide = new Location(point.x + 1, point.y);

                if (isWithinMaze(above) && isWithinMaze(below) && (stage[above.y][above.x] != Map.BEDROCK) &&
                        (stage[below.y][below.x] != Map.BEDROCK)) {
                    stage[point.y][point.x] = Map.DIRT;
                    implementedDoors++;
                } else {
                    if (isWithinMaze(leftSide) && isWithinMaze(rightSide) && (stage[leftSide.y][leftSide.x] != Map.BEDROCK) &&
                            (stage[rightSide.y][rightSide.x] != Map.BEDROCK)) {
                        stage[point.y][point.x] = Map.DIRT;
                        implementedDoors++;
                    }
                }
            }
        }
    }
}
