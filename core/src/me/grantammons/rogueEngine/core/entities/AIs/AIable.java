package me.grantammons.rogueEngine.core.entities.AIs;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;

public interface AIable {
    Location calculateMove(Map map);
}
