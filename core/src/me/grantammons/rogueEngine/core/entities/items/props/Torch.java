package me.grantammons.rogueEngine.core.entities.items.props;

import me.grantammons.rogueEngine.core.Notifier;

public class Torch extends Prop {

    public Torch(Notifier notifier) {
        super(notifier);

        isPickupable = false;
        spriteFile = "torch_unlit.png";

    }
}
