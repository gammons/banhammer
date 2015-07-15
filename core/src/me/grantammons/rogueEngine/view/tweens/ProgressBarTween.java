package me.grantammons.rogueEngine.view.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import me.grantammons.rogueEngine.view.utils.ProgressBar;

public class ProgressBarTween implements TweenAccessor<ProgressBar>{

    @Override
    public int getValues(ProgressBar bar, int tweenType, float[] ret) {
        ret[0] = bar.getX();
        ret[1] = bar.getY();
        return 2;
    }

    @Override
    public void setValues(ProgressBar bar, int i, float[] newValues) {
        bar.setX((int)newValues[0]);
        bar.setY((int)newValues[1]);
    }
}
