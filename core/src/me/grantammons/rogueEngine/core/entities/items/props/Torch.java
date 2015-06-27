package me.grantammons.rogueEngine.core.entities.items.props;

import me.grantammons.rogueEngine.core.Notifier;

public class Torch extends Prop {
    private Light light;

    public Torch(Notifier notifier) {
        super(notifier);
        isPickupable = false;
        spriteFile = "torch.png";
        light = new Light(notifier);
    }

    public Light getLight() {
        return light;
    }
}
