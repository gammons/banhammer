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
}
