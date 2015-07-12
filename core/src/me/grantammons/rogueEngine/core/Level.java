package me.grantammons.rogueEngine.core;

import me.grantammons.rogueEngine.LevelLike;

public abstract class Level implements LevelLike {

    protected Map map;
    protected Notifier notifier;

    public Level(Map map, Notifier notifier) {
        this.map = map;
        this.notifier = notifier;
    }

    public void load() {
        loadMap();
        loadItems();
        loadMonsters();
        loadProps();
        loadLights();
    }
}
