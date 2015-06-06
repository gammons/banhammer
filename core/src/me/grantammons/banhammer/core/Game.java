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

    private static final int TURN_LENGTH = 100;

    public Game() {
        player = new Player();
        player.x = 1;
        player.y = 1;

        map = new Map();
        entities = new ArrayList<Entity>();
        entities.add(player);
        scheduler = new Scheduler();
        scheduler.addEntity(player);
        generateMonsters();
        scheduler.nextEntity(); //make it the player's turn initially
    }

    public void tick() {
        // player should always be the next entity that needs to take a turn, when entering tick()
        Entity e = scheduler.currentEntity();
        System.out.println("current entity is "+e.name + " with speed "+e.speed);
        e.takeTurn();

        e = scheduler.nextEntity();
        System.out.println("NEXT entity is "+e.name + " with speed "+ e.speed);
        while (!e.equals(player)) {
            e.calculateMove(map);
            e.takeTurn();
            e = scheduler.nextEntity();
            System.out.println("NEXT NEXT entity is "+e.name + " with speed "+ e.speed);
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
