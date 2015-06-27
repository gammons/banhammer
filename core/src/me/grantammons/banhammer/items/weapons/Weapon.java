package me.grantammons.banhammer.items.weapons;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.Item;

public abstract class Weapon extends Item {
    public Weapon(Notifier notifier) {
        super(notifier);
        isPickupable = true;
    }

    public enum Types {
        Melee, Ranged, Bomb
    }

    protected Types type;

}
