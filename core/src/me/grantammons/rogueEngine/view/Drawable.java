package me.grantammons.rogueEngine.view;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by grantammons on 7/4/15.
 */
public interface Drawable {
    void draw(Batch batch, TweenManager tweenManager);
}
