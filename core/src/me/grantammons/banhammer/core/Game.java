package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;
import me.grantammons.banhammer.core.entities.Imp;
import me.grantammons.banhammer.core.entities.Monster;
import me.grantammons.banhammer.core.entities.playerClasses.Brute;
import me.grantammons.banhammer.core.utils.Scheduler;

import java.util.ArrayList;

/**
 * Created by grantammons on 5/31/15.
 */
public class Game {
    public Entity player;
    public Map map;
    private Scheduler scheduler;

    public Game() {
        player = new Brute();
        player.location = new Location(1,1);

        map = new Map();
        map.entities.add(player);
        scheduler = new Scheduler();
        generateMonsters();
    }

    public void tick() {
        processPlayerTurn();
        processEntityTurns();
    }

    public boolean playerCanMoveTo(int direction) {
        return map.canMove(Location.setLocationFromDirection(player.location, direction));
    }

    public ArrayList<Entity> getMonsters() {
        ArrayList<Entity> monsters = new ArrayList<Entity>();
        for(Entity e : map.entities) {
            if (e instanceof Monster) monsters.add(e);
        }
        return monsters;
    }

    private void processPlayerTurn() {
        Entity e = scheduler.currentEntity();
        if (e == null) e = scheduler.nextEntity(); // special case for first turn
        e.takeTurn(map);
    }

    private void processEntityTurns() {
        Entity e;
        e = scheduler.nextEntity();
        while (!e.equals(player)) {
            e.calculateMove(map);
            e.takeTurn(map);
            e = scheduler.nextEntity();
        }
    }

    private void generateMonsters() {
        Monster monster = new Imp();
        monster.location = new Location(3,3);
        map.entities.add(monster);

        for(Entity e : map.entities) {
            scheduler.addEntity(e);
        }
    }
}
