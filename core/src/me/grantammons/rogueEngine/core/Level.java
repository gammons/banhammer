package me.grantammons.rogueEngine.core;

import me.grantammons.rogueEngine.LevelLike;

public class Level implements LevelLike {

    protected Map map;
    protected Notifier notifier;
    protected Location playerSpawnLocation;
    private int[][] stage;

    public Level(Map map, Notifier notifier) {
        this.map = map;
        this.notifier = notifier;
    }

    public void load() {}

    @Override
    public Location getPlayerSpawnLocation() {
        return playerSpawnLocation;
    }

    public void setPlayerSpawnLocation(Location location) {
        playerSpawnLocation = location;
    }

    public void setStage(int[][] s) {
        this.stage = s;
        map.setMap(stage);
    }
}
