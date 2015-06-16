package me.grantammons.banhammer.core.entities;

import me.grantammons.banhammer.core.Notifier;

public abstract class Player extends Entity {
    public Player(Notifier notifier) {
        super(notifier);
    }
}
