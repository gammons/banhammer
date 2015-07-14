package me.grantammons.rogueEngine.core.entities.items;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

public abstract class Item extends AnimatedEntity implements ItemStatsInterface {
    protected int requiredStrength;
    protected int attack;
    protected int defense;
    protected int weight;

    protected boolean isExpired = false;
    protected boolean isPickupable = false;

    public Item(Notifier notifier) {
        super(notifier);
    }

    public String getName() {
        return name;
    }

    protected String unidentifiedName;

    public String getSpriteFile() {
        return spriteFile;
    }

    protected String spriteFile;

    public float getUnequippedSpeedModifier() {
        return 0;
    }

    @Override
    public float getEquippedSpeedModifier() {
        return 0;
    }

    @Override
    public float getUnequippedAttackModifier() {
        return 0;
    }

    @Override
    public float getEquippedAttackModifier() {
        return 0;
    }


        @Override
    public float getUnequippedStrengthModifier() {
        return 0;
    }

    @Override
    public float getEquippedStrengthModifier() {
        return 0;
    }

    @Override
    public float getUnequippedMagicModifier() {
        return 0;
    }

    @Override
    public float getEquippedMagicModifier() { return 0; }

    @Override
    public float getUnequippedSquirrelModifier() { return 0; }

    @Override
    public float getEquippedSquirrelModifier() { return 0; }

    @Override
    public float getUnequippedFortitudeModifier() {
        return 0;
    }

    @Override
    public float getEquippedFortitudeModifier() {
        return 0;
    }

    @Override
    public float getUnequippedCharmModifier() {
        return 0;
    }

    @Override
    public float getEquippedCharmModifier() {
        return 0;
    }

    @Override
    public float getUnequippedBrainpowerModifier() {
        return 0;
    }

    @Override
    public float getEquippedBrainpowerModifier() {
        return 0;
    }

    @Override
    public float getUnequippedDefenseModifier() {
        return 0;
    }

    @Override
    public float getEquippedDefenseModifier() {
        return 0;
    }

    public int getWeight() {
        return weight;
    }
    public boolean isExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public boolean isPickupable() {
        return isPickupable;
    }

}
