package me.grantammons.rogueEngine.core.entities.items.props;

import me.grantammons.rogueEngine.core.Notifier;

public class Light extends Prop {
    public Light(Notifier notifier) {
        super(notifier);
    }

    public enum LightTypes {Torch, Spotlight};
}
