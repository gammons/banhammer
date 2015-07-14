package me.grantammons.banhammer.items.weapons;

import me.grantammons.rogueEngine.core.Notifier;

/**
 * Created by grantammons on 6/14/15.
 */
public class TwoHandedSword extends Weapon {

    public TwoHandedSword(Notifier notifier) {
        super(notifier);
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

    @Override
    public float getStrengthModifier() {
        return 0;
    }

    @Override
    public float getMagicModifier() {
        return 0;
    }

    @Override
    public float getFortitudeModifier() {
        return 0;
    }

    @Override
    public float getCharmModifier() {
        return 0;
    }

    @Override
    public float getBrainpowerModifier() {
        return 0;
    }
}
