package me.grantammons.rogueEngine.tests;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.banhammer.entities.mobs.Imp;
import me.grantammons.banhammer.entities.playerClasses.Brute;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.banhammer.items.weapons.TwoHandedSword;
import org.testng.annotations.Test;

public class AttackTest {
    @Test(description="Ensure damage looks ok")
    public void testAttackDamage() {
        Notifier n = new Notifier();
        Brute b = new Brute(n);
        Imp i = new Imp(n);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        n.dump();
    }

    @Test(description="Weapons change the damage amount")
    public void testBetterWeapons() {
        Notifier n = new Notifier();
        Brute b = new Brute(n);
        Imp i = new Imp(n);
        Item sword = new TwoHandedSword(n);
        b.attack(i);
        b.attack(i);
        b.attack(i);
        b.setWeapon(sword);
        n.notify("Brute wields weapon!");
        b.attack(i);
        b.attack(i);
        b.attack(i);
        n.dump();
    }
}
