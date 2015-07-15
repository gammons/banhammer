package me.grantammons.rogueEngine.view.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteTween implements TweenAccessor<Sprite>{

    @Override
    public int getValues(Sprite sprite, int tweenType, float[] ret) {
        ret[0] = sprite.getX();
        ret[1] = sprite.getY();
        return 2;
    }

    @Override
    public void setValues(Sprite sprite, int i, float[] newValues) {
        sprite.setX(newValues[0]);
        sprite.setY(newValues[1]);
    }
}
