package me.grantammons.rogueEngine;

import me.grantammons.rogueEngine.core.Location;

public interface LevelLike {
    void load();
    Location getPlayerSpawnLocation();
    void loadMap();
    void loadItems();
    void loadMonsters();
    void loadProps();
    void loadLights();
}
