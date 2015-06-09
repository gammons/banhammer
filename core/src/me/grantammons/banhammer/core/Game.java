package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;
import me.grantammons.banhammer.core.entities.Imp;
import me.grantammons.banhammer.core.entities.Monster;
import me.grantammons.banhammer.core.entities.Player;
import me.grantammons.banhammer.core.utils.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grantammons on 5/31/15.
 */
public class Game {
    public Player player;
    public Map map;
    public List<Entity> entities;
    private Scheduler scheduler;

    public Game() {
        player = new Player();
        player.x = 1;
        player.y = 1;

        map = new Map();
        entities = new ArrayList<Entity>();
        entities.add(player);
        scheduler = new Scheduler();
        generateMonsters();
    }

    public void tick() {
        processPlayerTurn();
        processEntityTurns();
    }

    private void processPlayerTurn() {
        Entity e = scheduler.currentEntity();
        if (e == null) e = scheduler.nextEntity(); // special case for first turn
        e.takeTurn(map, entities);
    }

    private void processEntityTurns() {
        Entity e;
        e = scheduler.nextEntity();
        while (!e.equals(player)) {
            e.calculateMove(map);
            e.takeTurn(map, entities);
            e = scheduler.nextEntity();
        }
    }

    private void generateMonsters() {
        Monster monster = new Imp();
        monster.x = 3;
        monster.y = 3;
        entities.add(monster);

        for(Entity e : entities) {
            scheduler.addEntity(e);
        }
    }

    public boolean canMoveTo(Entity entity, int intendedDirection) {
        return map.canMove(entity, intendedDirection);
    }
}
