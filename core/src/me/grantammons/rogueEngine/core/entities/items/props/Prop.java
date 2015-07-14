package me.grantammons.rogueEngine.core.entities.items.props;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.Item;

public abstract class Prop extends Item {

    public Prop(Notifier notifier) {
        super(notifier);
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
