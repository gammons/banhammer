package me.grantammons.rogueEngine.core;

import me.grantammons.banhammer.entities.playerClasses.Brute;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.core.entities.mobs.Mob;
import me.grantammons.rogueEngine.core.utils.Scheduler;

import java.util.ArrayList;

/**
 * Created by grantammons on 5/31/15.
 */
public class Game {
    public AnimatedEntity player;
    public Map map;
    private Scheduler scheduler;
    private Notifier notifier;
    private Level level;

    public Game() {
        map = new Map();
        notifier = new Notifier();
        scheduler = new Scheduler();

        // temporary until we have better organization around levels.
        loadLevel(new Level());

        spawnPlayer();
    }

    public void loadLevel(Level level) {
        this.level = level;
        this.level.load(map, notifier);
        for(AnimatedEntity e : map.getEntities()) {
            System.out.println("Scheduler adding entities: "+e);
            scheduler.addEntity(e);
        }
    }

    public void tick() {
        processPlayerTurn();
        bringOutYourDead();
        processEntityTurns();
        itemPickups();
        bringOutYourDead();
    }

    public boolean playerCanMoveTo(int direction) {
        return map.canMove(Location.setLocationFromDirection(player.location, direction));
    }

    public ArrayList<AnimatedEntity> getMonsters() {
        ArrayList<AnimatedEntity> monsters = new ArrayList<AnimatedEntity>();
        for(AnimatedEntity e : map.getEntities()) {
            if (e instanceof Mob) monsters.add(e);
        }
        return monsters;
    }

    public ArrayList<String> recentNotifications() {
        return notifier.recent();
    }

    private void processPlayerTurn() {
        AnimatedEntity e = scheduler.currentEntity();
        if (e == null) e = scheduler.nextEntity(); // special case for first turn
        e.takeTurn(map);
    }

    private void processEntityTurns() {
        AnimatedEntity e;
        e = scheduler.nextEntity();
        while (!e.equals(player)) {
            e.calculateMove(map);
            e.takeTurn(map);
            e = scheduler.nextEntity();
        }
    }

    private void itemPickups() {
        for(Item i : map.getItems()) {
            for(AnimatedEntity e : map.getEntities()) {
                if (e.location.x == i.location.x && e.location.y == i.location.y) {
                    e.pickupItem(i);
                }
            }
        }
        map.getItems().removeIf(i -> i.isExpired());
    }

    private void generateMonsters() {

    }

    private void bringOutYourDead() {
        map.getEntities().forEach(e -> {
            if (e.isExpired()) {
                scheduler.remove(e);
                e.getSack().getItems().forEach(item -> {
                    notifier.notify(e.name + " drops a " + item.getName());
                    item.setIsExpired(false);
                    map.getItems().add(item);
                });
            }
        });
        map.getEntities().removeIf(e -> e.isExpired());

    }

    private void spawnPlayer() {
        player = new Brute(notifier);
        player.location = level.getPlayerSpawnLocation();
        map.getEntities().add(player);
        scheduler.addEntity(player);
    }

    public ArrayList<Item> getItems() {
        return map.getItems();
    }
}
