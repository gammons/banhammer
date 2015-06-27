package me.grantammons.rogueEngine.core.entities.items.props;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.Item;

public class Torch extends Item {
    private Light light;

    public Torch(Notifier notifier) {
        super(notifier);
        isPickupable = false;
        light = new Light(notifier);
    }

    public Light getLight() {
        return light;
    }
}
