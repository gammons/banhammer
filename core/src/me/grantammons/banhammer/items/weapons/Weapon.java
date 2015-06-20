package me.grantammons.banhammer.items.weapons;

import me.grantammons.rogueEngine.core.items.Item;

public abstract class Weapon extends Item {
    public enum Types {
        Melee, Ranged, Bomb
    }

    protected Types type;

}
