package me.grantammons.rogueEngine.core.entities.AIs;

import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

public abstract class AI {
    protected AnimatedEntity entity;

    public AI(AnimatedEntity entity) {
        this.entity = entity;
    }
}
