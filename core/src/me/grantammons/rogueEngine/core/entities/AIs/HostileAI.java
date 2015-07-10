package me.grantammons.rogueEngine.core.entities.AIs;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.entities.Entity;

public class HostileAI extends AI implements AIable {

    public HostileAI(Entity entity) {
        super(entity);
    }

    @Override
    public Location calculateMove(Map map) {
        return new Location();

    }
}
