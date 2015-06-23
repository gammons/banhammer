package me.grantammons.banhammer.items.weapons;

/**
 * Created by grantammons on 6/14/15.
 */
public class TwoHandedSword extends Weapon {

    public TwoHandedSword() {
        unidentifiedName = "A two-handed Sword";
        name = "A Sword";
        spriteFile = "sword.png";
    }

    public float getEquippedStrengthModifier() {
        return 5;
    }


    public float getEquippedSpeedModifier() {
        return -1;
    }
}
