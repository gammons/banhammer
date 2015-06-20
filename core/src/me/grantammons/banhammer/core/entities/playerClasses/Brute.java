package me.grantammons.banhammer.core.entities.playerClasses;

import me.grantammons.banhammer.core.Notifier;

public class Brute extends PlayerClass {
    public static float STRENGTH_MODIFIER = 0.85f;
    public static float MAGIC_MODIFIER = 0.15f;
    public static float FORTITUDE_MODIFIER = 0.85f;
    public static float CHARM_MODIFIER = 0.15f;
    public static float BRAINPOWER_MODIFIER = 0.15f;

    public Brute(Notifier notifier) {
        super(notifier);
        name = "Brute";
        hp = 10;
        speed = 5;
    }

    @Override
    public float getStrengthModifier() {
        return STRENGTH_MODIFIER;
    }

    @Override
    public float getMagicModifier() {
        return MAGIC_MODIFIER;
    }

    @Override
    public float getFortitudeModifier() {
        return FORTITUDE_MODIFIER;
    }

    @Override
    public float getCharmModifier() {
        return CHARM_MODIFIER;
    }

    @Override
    public float getBrainpowerModifier() {
        return BRAINPOWER_MODIFIER;
    }
}
