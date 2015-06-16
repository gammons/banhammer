package me.grantammons.banhammer.core.entities.mobs;

import me.grantammons.banhammer.core.Notifier;

/**
 * Created by grantammons on 6/5/15.
 */
public class Imp extends Monster {
    public Imp(Notifier notifier) {
        super(notifier);
        speed = 15;
    }

    @Override
    public float getStrengthModifier() {
        return 0.05f;
    }

    @Override
    public float getMagicModifier() {
        return 0;
    }

    @Override
    public float getFortitudeModifier() {
        return 0.05f;
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
