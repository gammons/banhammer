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
        fillStage();
        rooms = new ArrayList<>();
    }

    private void findPlayerSpawn() {
        boolean found = false;

        int x = 0;
        int y = 0;

        while(found == false) {
            x = MathUtils.random(0, STAGE_WIDTH - 1);
            y = MathUtils.random(0, STAGE_HEIGHT - 1);
            if (stage[y][x] == Map.GROUND) found = true;
        }
        level.setPlayerSpawnLocation(new Location(x,y));
    }

    public Level generate() {
        fillRooms();
        findPlayerSpawn();
        mazeEmptyAreas();
        level.setStage(stage);
        return level;
    }

    private void mazeEmptyAreas() {
        for(int y = 1; y < STAGE_HEIGHT; y+= 2) {
            for (int x = 1; x < STAGE_WIDTH; x+= 2) {
                if (stage[y][x] == Map.GROUND) continue;
                growMaze(x, y);
            }
        }
    }

    private void growMaze(int x, int y) {
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
            int rectangularity = (int) (MathUtils.randomTriangular(0, 1 + size / 2) * 2);

            width = size;
            height = size;

            if (MathUtils.randomBoolean()) {
                width += rectangularity;
            } else {
                height += rectangularity;
            }

            x = MathUtils.random(0, STAGE_WIDTH - width);
            y = MathUtils.random(0, STAGE_HEIGHT - height);
        }

        public boolean intersects(Room other) {
            return Intersector.intersectRectangles(getRectangle(), other.getRectangle(), new Rectangle());
        }

        public Rectangle getRectangle() {
            return new Rectangle(this.x, this.y, this.width, this.height);
        }
    }
}
