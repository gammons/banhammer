package me.grantammons.banhammer.core.entities;

import me.grantammons.banhammer.core.*;
import me.grantammons.banhammer.core.items.Item;

/**
 * Created by grantammons on 5/31/15.
 */

public class Entity {
    public Location location;
    public Location intendedLocation;

    public String name;

    protected int speed;

    protected int strength;
    protected int magic;
    protected int fortitude; //dexterity
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

    public Entity() {

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

    private void attack(Entity attackable) {
        System.out.println("attacking "+attackable.name);
        Combat.attack(this, attackable);
    }

    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

    public void move() {
        location = intendedLocation;
    }

    public void calculateMove(Map map) {

    }

}

