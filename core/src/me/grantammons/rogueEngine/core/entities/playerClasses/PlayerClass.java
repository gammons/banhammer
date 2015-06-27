package me.grantammons.rogueEngine.core.entities.playerClasses;

import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

public abstract class PlayerClass extends AnimatedEntity {
    public static final int MAX_STAT = 18;

    public PlayerClass(Notifier notifier) {
        super(notifier);
        genStats();
    }

    private void genStats() {
        strength = genStat(getStrengthModifier());
        magic = genStat(getMagicModifier());
        fortitude = genStat(getFortitudeModifier());
        charm = genStat(getCharmModifier());
        brainpower = genStat(getBrainpowerModifier());
    }

    private int genStat(float modifier) {
        int stat = (int)(MAX_STAT * modifier + MathUtils.random(-3, 3));
        stat = stat > 18 ? 18 : stat;
        stat = stat <= 0 ? 1 : stat;
        return stat ;
    }

    public String toString() {
        String ret = "";
        ret += "Player " + this.name + "of type " + this.getClass() + "\n";
        ret += "Strength: " + this.strength + "\n";
        ret += "Magic: " + this.magic + "\n";
        ret += "Fortitude: " + this.fortitude + "\n";
        ret += "Charm: " + this.charm + "\n";
        ret += "Brainpower: " + this.brainpower + "\n";
        return ret;
    }
}
