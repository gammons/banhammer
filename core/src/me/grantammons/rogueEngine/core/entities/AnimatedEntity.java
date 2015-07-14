package me.grantammons.rogueEngine.core.entities;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.*;
import me.grantammons.rogueEngine.core.entities.AIs.AIable;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.core.entities.playerClasses.PlayerClass;
import me.grantammons.rogueEngine.core.utils.fov.Fov;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class AnimatedEntity extends Entity implements StatsInterface {

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

    private Fov fov;
    private Set<Location> visitedTiles;
    private ArrayList<Entity> previouslyNoticedEntities;
    private ArrayList<Entity> newlyNoticedEntities;
    private ArrayList<Location> visibleTiles;

    protected AIable ai;

    public AnimatedEntity(Notifier notifier) {
        super(notifier);
        sack = new Sack();
        visitedTiles = new HashSet<>();
        visibleTiles  = new ArrayList<>();
        previouslyNoticedEntities = new ArrayList<>();
        newlyNoticedEntities = new ArrayList<>();
    }


    public void takeTurn(Map map) {
        unnoticeNewThings();
        if (hasAI()) calculateMove(map);
        if (hasPath()) followPath();
        processMove(map);
        noticeThings(map);
    }

    private void unnoticeNewThings() {
        previouslyNoticedEntities.addAll(newlyNoticedEntities);
        previouslyNoticedEntities.add(this);
    }

    private void noticeThings(Map map) {
        ArrayList<Entity> mapEntities = (ArrayList<Entity>) map.getEntities().clone();
        mapEntities.removeAll(previouslyNoticedEntities);

        for (Entity e : mapEntities) {
            if (visibleTiles.stream().anyMatch(e.location::equals)) {
                if (this instanceof PlayerClass)
                    notifier.notify("You noticed "+e.name);
                newlyNoticedEntities.add(e);
            }
        }
        previouslyNoticedEntities.clear();
    }

    public void clearNewlyNoticedEntities() {
        newlyNoticedEntities.clear();
    }

    private void followPath() {
        intendedLocation = path.get(0);
        path.remove(0);
    }

    public boolean hasAI() {
        return (ai != null);
    }

    private void processMove(Map map) {
        AnimatedEntity attackable = map.entityAt(intendedLocation);
        if (attackable != null) {
            attack(attackable);
            intendedLocation = location;
        } else if (map.canDig(intendedLocation)) {
            map.dig(intendedLocation);
        } else if (map.canMove(intendedLocation)) {
            move();
            calculateFov(map);
        }
    }

    public int getSpeed() {
        int s = speed;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedSpeedModifier();
        }
        if (hat != null) s += hat.getEquippedSpeedModifier();
        if (shirt != null) s += shirt.getEquippedSpeedModifier();
        if (pants != null) s += pants.getEquippedSpeedModifier();
        if (shoes != null) s += shoes.getEquippedSpeedModifier();
        if (gloves != null) s += gloves.getEquippedSpeedModifier();
        if (weapon != null) s += weapon.getEquippedSpeedModifier();
        return s;
    }

    public int getStrength() {
        int s = strength;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedStrengthModifier();
        }
        if (hat != null) s += hat.getEquippedStrengthModifier();
        if (shirt != null) s += shirt.getEquippedStrengthModifier();
        if (pants != null) s += pants.getEquippedStrengthModifier();
        if (shoes != null) s += shoes.getEquippedStrengthModifier();
        if (gloves != null) s += gloves.getEquippedStrengthModifier();
        if (weapon != null) s += weapon.getEquippedStrengthModifier();
        return s;
    }

    public int getMagic() {
        int s = magic;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedMagicModifier();
        }
        if (hat != null) s += hat.getEquippedMagicModifier();
        if (shirt != null) s += shirt.getEquippedMagicModifier();
        if (pants != null) s += pants.getEquippedMagicModifier();
        if (shoes != null) s += shoes.getEquippedMagicModifier();
        if (gloves != null) s += gloves.getEquippedMagicModifier();
        if (weapon != null) s += weapon.getEquippedMagicModifier();
        return s;
    }

    public int getFortitude() {
        int s = fortitude;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedFortitudeModifier();
        }
        if (hat != null) s += hat.getEquippedFortitudeModifier();
        if (shirt != null) s += shirt.getEquippedFortitudeModifier();
        if (pants != null) s += pants.getEquippedFortitudeModifier();
        if (shoes != null) s += shoes.getEquippedFortitudeModifier();
        if (gloves != null) s += gloves.getEquippedFortitudeModifier();
        if (weapon != null) s += weapon.getEquippedFortitudeModifier();
        return s;
    }

    public int getCharm() {
        int s = charm;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedCharmModifier();
        }
        if (hat != null) s += hat.getEquippedCharmModifier();
        if (shirt != null) s += shirt.getEquippedCharmModifier();
        if (pants != null) s += pants.getEquippedCharmModifier();
        if (shoes != null) s += shoes.getEquippedCharmModifier();
        if (gloves != null) s += gloves.getEquippedCharmModifier();
        if (weapon != null) s += weapon.getEquippedCharmModifier();
        return s;
    }

    public int getBrainpower() {
        int s = brainpower;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedBrainpowerModifier();
        }
        if (hat != null) s += hat.getEquippedBrainpowerModifier();
        if (shirt != null) s += shirt.getEquippedBrainpowerModifier();
        if (pants != null) s += pants.getEquippedBrainpowerModifier();
        if (shoes != null) s += shoes.getEquippedBrainpowerModifier();
        if (gloves != null) s += gloves.getEquippedBrainpowerModifier();
        if (weapon != null) s += weapon.getEquippedBrainpowerModifier();
        return s;
    }

    public int getSquirrel() {
        int s = squirrel;
        for(Item i : sack.getItems()) {
            s += i.getUnequippedSquirrelModifier();
        }
        if (hat != null) s += hat.getEquippedSquirrelModifier();
        if (shirt != null) s += shirt.getEquippedSquirrelModifier();
        if (pants != null) s += pants.getEquippedSquirrelModifier();
        if (shoes != null) s += shoes.getEquippedSquirrelModifier();
        if (gloves != null) s += gloves.getEquippedSquirrelModifier();
        if (weapon != null) s += weapon.getEquippedSquirrelModifier();
        return s;
    }

    public float getOffense() {
        float f = getStrength();
        for(Item i : sack.getItems()) {
            f += i.getUnequippedAttackModifier();
        }
        if (hat != null) f += hat.getEquippedAttackModifier();
        if (shirt != null) f += shirt.getEquippedAttackModifier();
        if (pants != null) f += pants.getEquippedAttackModifier();
        if (shoes != null) f += shoes.getEquippedAttackModifier();
        if (gloves != null) f += gloves.getEquippedAttackModifier();
        if (weapon != null) f += weapon.getEquippedAttackModifier();
        return f;
    }

    public float getDefense() {
        float f = getFortitude();
        for(Item i : sack.getItems()) {
            f += i.getUnequippedDefenseModifier();
        }
        if (hat != null) f += hat.getEquippedDefenseModifier();
        if (shirt != null) f += shirt.getEquippedDefenseModifier();
        if (pants != null) f += pants.getEquippedDefenseModifier();
        if (shoes != null) f += shoes.getEquippedDefenseModifier();
        if (gloves != null) f += gloves.getEquippedDefenseModifier();
        if (weapon != null) f += weapon.getEquippedDefenseModifier();
        return f;
    }

    public float getModifier(String stat) {
        float f = getFortitude();
        for(Item i : sack.getItems()) {
            f += i.getUnequippedDefenseModifier();
        }
        if (hat != null) f += hat.getEquippedDefenseModifier();
        if (shirt != null) f += shirt.getEquippedDefenseModifier();
        if (pants != null) f += pants.getEquippedDefenseModifier();
        if (shoes != null) f += shoes.getEquippedDefenseModifier();
        if (gloves != null) f += gloves.getEquippedDefenseModifier();
        if (weapon != null) f += weapon.getEquippedDefenseModifier();
        return f;
    }

    public Item getHat() {
        return hat;
    }

    public void setHat(Item hat) {
        this.hat = hat;
    }

    public Item getShirt() {
        return shirt;
    }

    public void setShirt(Item shirt) {
        this.shirt = shirt;
    }

    public Item getPants() {
        return pants;
    }

    public void setPants(Item pants) {
        this.pants = pants;
    }

    public Item getShoes() {
        return shoes;
    }

    public void setShoes(Item shoes) {
        this.shoes = shoes;
    }

    public Item getGloves() {
        return gloves;
    }

    public void setGloves(Item gloves) {
        this.gloves = gloves;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public Sack getSack() {
        return sack;
    }

    public void die() {
        notifier.notify(name + " is killed!");
        isExpired = true;
    }

    public float dodgeNumber() {
        return getSpeed() + getSquirrel();
    }

    public void setSpeed(int speed) { this.speed = speed; }

    public void move() {
        location = intendedLocation;
        sack.getItems().forEach(i -> i.location = location);
    }

    public void calculateMove(Map map) {
        if (hasAI()) ai.calculateMove(map);
    }

    public void attack(AnimatedEntity victim) {
        System.out.println("Attacker:");
        System.out.println(this);
        System.out.println("Victom:");
        System.out.println(victim);
        if (getHitPercentage(victim.dodgeNumber()) > MathUtils.randomTriangular(0, 1)) {
            //hit
            float damage = getOffense() - victim.getDefense();
            damage += MathUtils.randomTriangular((float)(-1 * (damage * 0.2)), (float)(damage * 0.2));
            if (damage <= 0) damage = 1;

            notifier.notify(name + " hits " + victim.name + " for " + (int)damage + " damage.");
            victim.receiveDamage(damage);
        } else {
            //missed
            notifier.notify(name + " misses " + victim.name);
        }
    }

    private float getHitPercentage(float defense) {
        return ((defense + getSquirrel()) / getStrength());
    }

    private void receiveDamage(float amount) {
        hp -= (int)amount;
        if (hp <= 0) die();
    }

    public void pickupItem(Item i) {
        if (i.isPickupable()) {
            notifier.notify(name + " picks up " + i.getName());
            sack.getItems().add(i);
            i.setIsExpired(true);
        }
    }

    public ArrayList<Location> getVisibleTiles() {
        return fov.getVisibleTiles();
    }

    public ArrayList<Location> getVisitedTiles() {
        ArrayList<Location> ret = new ArrayList<>();
        ret.addAll(visitedTiles);
        return ret;
    }

    public void calculateFov(Map map) {
        fov = new Fov(map.getMap());
        fov.calculateFov(location, map.getAmbientLightAt(location));
        visitedTiles.addAll(fov.getVisibleTiles());
        visibleTiles = fov.getVisibleTiles();
    }

    public boolean needsHumanInput() {
        return newlyNoticedEntities.size() > 0 || (!hasPath() && (intendedLocation.equals(location)));
    }
}

