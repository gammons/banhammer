package me.grantammons.rogueEngine.core;

import me.grantammons.banhammer.entities.playerClasses.Brute;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.core.utils.eventing.Scheduler;

import java.util.ArrayList;

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
        player = new Brute(notifier);
        scheduler.addEntity(player);

        // temporary until we have better organization around levels.
        loadLevel(new Level());

        spawnPlayer();
    }

    public void loadLevel(Level level) {
        this.level = level;
        this.level.load(map, notifier);
        loadMonsters();
    }

    public void tick() {
        Entity e = scheduler.nextEntity();
        player.clearNewlyNoticedEntities();
        while (continueProcessing(e)) {
            e.takeTurn(map);
            bringOutYourDead();
            itemPickups();
            e = scheduler.nextEntity();
        }
    }

    private boolean continueProcessing(Entity e) {
        return !e.equals(player) || !player.needsHumanInput();
    }

    public boolean playerCanMoveTo(int direction) {
        return direction == Constants.WAIT || map.canMove(Location.setLocationFromDirection(player.location, direction));
    }


    public ArrayList<String> recentNotifications() {
        return notifier.recent();
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

    private void bringOutYourDead() {
        map.getEntities().forEach(e -> {
            if (e.isExpired()) {
                scheduler.remove(e);
                dropTheSack(e);
            }
        });
        map.getEntities().removeIf(e -> e.isExpired());

    }

    private void dropTheSack(AnimatedEntity e) {
        e.getSack().getItems().forEach(item -> {
            notifier.notify(e.name + " drops a " + item.getName());
            item.setIsExpired(false);
            map.getItems().add(item);
        });
    }

    private void spawnPlayer() {
        player.location = level.getPlayerSpawnLocation();
        map.getEntities().add(player);

        // I don't know about this.
        player.calculateFov(map);
    }

    private void loadMonsters() {
        for (AnimatedEntity e : map.getMonsters()) {
            scheduler.addEntity(e);
        }
    }

    public ArrayList<Item> getItems() {
        return map.getItems();
    }
}
