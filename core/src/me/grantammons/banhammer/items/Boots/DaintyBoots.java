package me.grantammons.banhammer.items.Boots;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.Item;

/**
 * Created by grantammons on 6/16/15.
 */
public class DaintyBoots extends Item {
    public DaintyBoots(Notifier notifier) {
        super(notifier);
    }

    public float getEquippedFortitudeModifier() {
        return 1;
    }

    public float getEquippedSquirrelModifier() {
        return 2.6f;
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
