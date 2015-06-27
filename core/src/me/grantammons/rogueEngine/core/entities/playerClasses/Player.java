package me.grantammons.rogueEngine.core.entities.playerClasses;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

public abstract class Player extends AnimatedEntity {
    public Player(Notifier notifier) {
        super(notifier);
    }
}
