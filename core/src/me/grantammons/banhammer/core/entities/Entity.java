package me.grantammons.banhammer.core.entities;

import me.grantammons.banhammer.core.*;
import me.grantammons.banhammer.core.items.Item;

/**
 * Created by grantammons on 5/31/15.
 */

public abstract class Entity implements StatsInterface {
    public Location location;
    public Location intendedLocation;

    public String name;

    protected int speed;

    protected int strength;
    protected int magic;
    protected int fortitude; //constitution
    protected int squirrel; //dexterity
    protected int charm;
    protected int brainpower;

    protected int hp;
    protected int maxHp;

    protected Item weapon;
    protected Item secondWeapon;

    protected Item hat;
    protected Item shirt;
    protected Item pants;
    protected Item shoes;
    protected Item gloves;
    protected Sack sack;

    public int intendedDirection = Constants.NO_DIRECTION;

    protected Notifier notifier;

    public Entity(Notifier notifier) {
        sack = new Sack();
        this.notifier = notifier;
    }

    public void takeTurn(Map map) {
        if (intendedDirection >= 0) {
            intendedLocation = Location.setLocationFromDirection(location, intendedDirection);
            Entity attackable = map.entityAt(intendedLocation);
            if (attackable != null) {
                attack(attackable);
            } else if (map.canDig(intendedLocation)) {
                map.dig(intendedLocation);
            } else if (map.canMove(intendedLocation)) {
                move();
            }
        } else {
            // do some other shit
        }
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public void move() {
        location = intendedLocation;
    }

    public void calculateMove(Map map) {

    }

    public float attackNumber() {
        float str = strength;
        for(Item i : sack.getItems()) {
            str += i.getUnequippedStrengthModifier();
        }
        str += weapon.getEquippedStrengthModifier();
        return str;
    }

    public float defendNumber() {
        float f = fortitude;
        for(Item i : sack.getItems()) {
            f += i.getUnequippedFortitudeModifier();
        }
        if (hat != null) f += hat.getEquippedFortitudeModifier();
        if (shirt != null) f += shirt.getEquippedFortitudeModifier();
        if (pants != null) f += pants.getEquippedFortitudeModifier();
        if (shoes != null) f += shoes.getEquippedFortitudeModifier();
        if (gloves != null) f += gloves.getEquippedFortitudeModifier();
        return f;
    }

    public float dodgeNumber() {
        return speed + squirrel;
    }

    public int getMagic() {
        return magic;
    }

    public int getFortitude() {
        return fortitude;
    }

    public int getCharm() {
        return charm;
    }

    public int getBrainpower() {
        return brainpower;
    }

    private void attack(Entity victim) {
    }
}

