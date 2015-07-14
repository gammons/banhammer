package me.grantammons.banhammer.items.Boots;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.Item;

public class SpeedBoots extends Item {
    public SpeedBoots(Notifier notifier) {
        super(notifier);
        name = "Super duper speed boots";
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
