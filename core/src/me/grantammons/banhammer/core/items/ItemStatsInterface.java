package me.grantammons.banhammer.core.items;

/**
 * Created by grantammons on 6/15/15.
 */
public interface ItemStatsInterface {
    float getUnequippedStrengthModifier();
    float getEquippedStrengthModifier();

    float getUnequippedMagicModifier();
    float getEquippedMagicModifier();

    float getUnequippedFortitudeModifier();
    float getEquippedFortitudeModifier();

    float getUnequippedCharmModifier();
    float getEquippedCharmModifier();

    float getUnequippedBrainpowerModifier();
    float getEquippedBrainpowerModifier();
}
