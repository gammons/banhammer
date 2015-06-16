package me.grantammons.banhammer.core.items.weapons;

import me.grantammons.banhammer.core.items.Item;

public abstract class Weapon extends Item {
    public enum Types {
        Melee, Ranged, Bomb
    }

    protected Types type;

}
