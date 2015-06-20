package me.grantammons.banhammer.core.items;

/**
 * Created by grantammons on 6/15/15.
 */
public interface ItemStatsInterface {
    float getUnequippedAttackModifier();
    float getEquippedAttackModifier();

    float getUnequippedDefenseModifier();
    float getEquippedDefenseModifier();

    float getUnequippedSpeedModifier();
    float getEquippedSpeedModifier();

    float getUnequippedStrengthModifier();
    float getEquippedStrengthModifier();

    float getUnequippedMagicModifier();
    float getEquippedMagicModifier();

    float getUnequippedFortitudeModifier();
    float getEquippedFortitudeModifier();

    float getUnequippedSquirrelModifier();
    float getEquippedSquirrelModifier();

    float getUnequippedCharmModifier();
    float getEquippedCharmModifier();

    float getUnequippedBrainpowerModifier();
    float getEquippedBrainpowerModifier();
}
