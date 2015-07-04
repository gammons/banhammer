package me.grantammons.rogueEngine.core.entities.items.Lights;

import me.grantammons.rogueEngine.core.Notifier;

public class TorchLight extends Light {

    public TorchLight(Notifier notifier) {
        super(notifier);
        brightness = 64;
        flickering = true;
    }
}
