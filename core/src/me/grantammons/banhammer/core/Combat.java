package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.entities.Entity;

/**
 * Created by grantammons on 6/12/15.
 */
public class Combat {
    private Entity attacker;
    private Entity victim;
    private Notifier notifier;

    public Combat(Entity attacker, Entity victim, Notifier notifier) {
        this.attacker = attacker;
        this.victim = victim;
        this.notifier = notifier;

    }
    public void execute() {
        //attacker.getAttack() - victim.getDefense();
    }
}
