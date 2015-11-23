package me.grantammons.rogueEngine.view.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CamZoomTween implements TweenAccessor<OrthographicCamera>{
    @Override
    public int getValues(OrthographicCamera orthographicCamera, int tweenType, float[] ret) {
        ret[0] = orthographicCamera.viewportWidth;
        ret[1] = orthographicCamera.viewportHeight;
        return 2;
    }

    @Override
    public void setValues(OrthographicCamera orthographicCamera, int tweenType, float[] newValues) {
        orthographicCamera.viewportWidth = newValues[0];
        orthographicCamera.viewportHeight = newValues[1];
        orthographicCamera.update();
    }
}
