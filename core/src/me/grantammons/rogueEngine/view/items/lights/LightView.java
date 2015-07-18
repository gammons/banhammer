package me.grantammons.rogueEngine.view.items.lights;

import aurelienribon.tweenengine.TweenManager;
import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.view.Drawable;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class LightView implements Drawable {
    protected Light light;
    protected RayHandler rayHandler;

    protected me.grantammons.rogueEngine.core.entities.items.Lights.Light lightModel;
    protected float redColor;
    protected float greenColor;
    protected float blueColor;
    private int counter = 0;

    public LightView(me.grantammons.rogueEngine.core.entities.items.Lights.Light light, RayHandler rayHandler) {
        this.lightModel = light;
        this.light = new PointLight(rayHandler, 128, null, lightModel.getBrightness(), 0f, 0f);
        this.rayHandler = rayHandler;
    }

    @Override
    public void draw(Batch batch, TweenManager tweenManager) {
        light.setPosition(getX(), getY());
        if (lightModel.isFlickering())
            setIntensity();
    }

    @Override
    public void updateDelta(float delta) {

    }

    public void drawAt(float x, float y) {
        light.setPosition(x + (PIXEL_WIDTH / 2), y + (PIXEL_HEIGHT / 2));
        if (lightModel.isFlickering())
            setIntensity();
    }

    public me.grantammons.rogueEngine.core.entities.items.Lights.Light getLightModel() {
        return lightModel;
    }


    private float getX() {
        return lightModel.location.x * PIXEL_WIDTH + (PIXEL_WIDTH / 2);
    }

    private float getY() {
        return lightModel.location.y * PIXEL_HEIGHT + (PIXEL_HEIGHT / 2);
    }

    private void setIntensity() {
        counter ++;
        if (counter % 5 == 0) {
            float intensity = MathUtils.randomTriangular(0.8f, 0.85f);
            light.setColor(this.redColor, this.greenColor, this.blueColor, intensity);
            light.setDistance(MathUtils.randomTriangular(lightModel.getBrightness() - 4, lightModel.getBrightness() + 4));
            counter = 0;
        }
    }

    public void setVisible(boolean visible) {
        this.light.setActive(visible);
    }
}
