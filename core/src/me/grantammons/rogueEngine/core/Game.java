package me.grantammons.rogueEngine.core;

import me.grantammons.banhammer.entities.mobs.Imp;
import me.grantammons.banhammer.entities.playerClasses.Brute;
import me.grantammons.banhammer.items.weapons.TwoHandedSword;
import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.rogueEngine.core.entities.mobs.Mob;
import me.grantammons.rogueEngine.core.items.Item;
import me.grantammons.rogueEngine.core.utils.Scheduler;

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

        TwoHandedSword sword = new TwoHandedSword();
        sword.location = new Location(7,7);
        map.items.add(sword);

        generateMonsters();
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

    public ArrayList<Entity> getMonsters() {
        ArrayList<Entity> monsters = new ArrayList<Entity>();
        for(Entity e : map.entities) {
            if (e instanceof Mob) monsters.add(e);
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

    private void itemPickups() {
        for(Item i : map.items) {
            for(Entity e : map.entities) {
                if (e.location.x == i.location.x && e.location.y == i.location.y) {
                    e.pickupItem(i);
                }
            }
        }
        map.items.removeIf(i -> i.isExpired());
    }

    private void generateMonsters() {
        Mob mob = new Imp(notifier);
        mob.location = new Location(3,3);
        map.entities.add(mob);

        Mob mob2 = new Imp(notifier);
        mob2.location = new Location(6,6);
        map.entities.add(mob2);

        Mob mob3 = new Imp(notifier);
        mob3.location = new Location(9,9);
        map.entities.add(mob3);

        for(Entity e : map.entities) {
            scheduler.addEntity(e);
        }
    }

    private void bringOutYourDead() {
        map.entities.forEach(e -> {
            if (e.isExpired()) {
                scheduler.remove(e);
                e.getSack().getItems().forEach(item -> {
                    notifier.notify(e.name + " drops a "+item.getName());
                    item.setIsExpired(false);
                    map.items.add(item);
                });
            }
        });
        map.entities.removeIf(e -> e.isExpired());

    }

    public ArrayList<Item> getItems() {
        return map.items;
    }
}
