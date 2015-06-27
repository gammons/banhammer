package me.grantammons.rogueEngine.core.entities.items.props;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.Entity;

public class Light extends Entity {
    public Light(Notifier notifier) {
        super(notifier);
    }

    public enum LightTypes {Torch, Spotlight};
}
