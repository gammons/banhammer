package me.grantammons.rogueEngine.core.entities.AIs;

import me.grantammons.rogueEngine.core.entities.Entity;

public abstract class AI {
    protected Entity entity;

    public AI(Entity entity) {
        this.entity = entity;
    }
}
