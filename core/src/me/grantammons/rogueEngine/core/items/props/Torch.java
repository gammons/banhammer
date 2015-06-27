package me.grantammons.rogueEngine.core.items.props;

import me.grantammons.rogueEngine.core.items.Item;

public class Torch extends Item {
    private Light light;

    public Torch() {
        isPickupable = false;
        light = new Light();
    }

    public Light getLight() {
        return light;
    }
}
