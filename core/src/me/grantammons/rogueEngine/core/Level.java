package me.grantammons.rogueEngine.core;

import me.grantammons.banhammer.entities.mobs.Imp;
import me.grantammons.banhammer.items.weapons.TwoHandedSword;
import me.grantammons.rogueEngine.core.entities.items.Lights.TorchLight;
import me.grantammons.rogueEngine.core.entities.items.props.Torch;
import me.grantammons.rogueEngine.core.entities.mobs.Mob;

/**
 * Created by grantammons on 6/27/15.
 */
public class Level {

    private int[][] generatedMap = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private Map map;
    private Notifier notifier;

    public Level() {
    }

    public void load(Map map, Notifier notifier) {
        this.map = map;
        this.notifier = notifier;
        map.setMap(generatedMap);

        loadItems();
        loadMonsters();
        loadProps();
        loadLights();

    }

    public Location getPlayerSpawnLocation() {
        return new Location(1,6);
    }

    private void loadItems() {
        TwoHandedSword sword = new TwoHandedSword(notifier);
        sword.location = new Location(7,7);
        map.getItems().add(sword);
    }

    private void loadMonsters() {
        Mob mob = new Imp(notifier);
        mob.location = new Location(3,3);
        map.getEntities().add(mob);

        Mob mob2 = new Imp(notifier);
        mob2.location = new Location(6,6);
        map.getEntities().add(mob2);

        Mob mob3 = new Imp(notifier);
        mob3.location = new Location(9,9);
        map.getEntities().add(mob3);

    }

    private void loadProps() {
        Torch t = new Torch(notifier);
        t.location = new Location(5,2);
        map.getProps().add(t);

        Torch t2 = new Torch(notifier);
        t2.location = new Location(9,2);
        map.getProps().add(t2);

        Torch t3 = new Torch(notifier);
        t3.location = new Location(9,9);
        map.getProps().add(t3);

        Torch t4 = new Torch(notifier);
        t4.location = new Location(4,9);
        map.getProps().add(t4);
    }

    private void loadLights() {
        TorchLight t = new TorchLight(notifier);
        t.location = new Location(5,2);
        map.getLights().add(t);

        TorchLight t2 = new TorchLight(notifier);
        t2.location = new Location(9,2);
        map.getLights().add(t2);

        TorchLight t3 = new TorchLight(notifier);
        t3.location = new Location(9,9);
        map.getLights().add(t3);
    }
}
