package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;
import me.grantammons.banhammer.core.entities.mobs.Imp;
import me.grantammons.banhammer.core.entities.mobs.Monster;
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
    private Notifier notifier;

    public Game() {
        notifier = new Notifier();
        player = new Brute(notifier);
        player.location = new Location(1,1);

        map = new Map();
        map.entities.add(player);
        scheduler = new Scheduler();
        generateMonsters();
    }

    public void tick() {
        processPlayerTurn();
        bringOutYourDead();
        processEntityTurns();
        bringOutYourDead();
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

    public ArrayList<String> recentNotifications() {
        return notifier.recent();
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
        Monster monster = new Imp(notifier);
        monster.location = new Location(3,3);
        map.entities.add(monster);

        for(Entity e : map.entities) {
            scheduler.addEntity(e);
        }
    }

    private void bringOutYourDead() {
        map.entities.forEach(e -> {
            if (e.isDead()) scheduler.remove(e);
        });
        map.entities.removeIf(e -> e.isDead());

    }
}
