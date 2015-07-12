package me.grantammons.rogueEngine.core.entities.AIs;

import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.rogueEngine.core.entities.playerClasses.PlayerClass;
import me.grantammons.rogueEngine.core.utils.pathfinding.AStar;

import java.util.ArrayList;

public class HostileAI extends AI implements AIable {

    public HostileAI(AnimatedEntity entity) {
        super(entity);
    }

    @Override
    public void calculateMove(Map map) {
        entity.calculateFov(map);
        entity.getVisibleTiles().forEach(e -> {
            Entity potential = map.entityAt(e);
            if (potential instanceof PlayerClass) {
                AStar a = new AStar(potential.location, map);
                ArrayList<Location> path = a.compute(entity.location);
                entity.setPath(path);
            }
        });
        if (!entity.hasPath()) {
            RandomAI r = new RandomAI(entity);
            r.calculateMove(map);
        }
    }
}
