package me.grantammons.rogueEngine.core.items;

import me.grantammons.rogueEngine.core.Location;

public abstract class Item implements ItemStatsInterface {
    int requiredStrength;
    int attack;
    int defense;
    int weight;
    private boolean isExpired = false;

    public String getName() {
        return name;
    }

    protected String name;
    protected String unidentifiedName;
    public Location location;

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

}
