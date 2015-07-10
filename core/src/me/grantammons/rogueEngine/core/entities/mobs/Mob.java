package me.grantammons.rogueEngine.core.entities.mobs;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

public abstract class Mob extends AnimatedEntity {
    public Mob(Notifier notifier) {
        super(notifier);
    }
}
