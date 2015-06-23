package me.grantammons.banhammer.entities.mobs;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.mobs.Mob;

/**
 * Created by grantammons on 6/5/15.
 */
public class Imp extends Mob {
    public Imp(Notifier notifier) {
        super(notifier);
        name = "Imp";
        hp = 5;
        speed = 5;
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