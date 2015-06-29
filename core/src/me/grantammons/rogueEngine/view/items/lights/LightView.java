package me.grantammons.rogueEngine.view.items.lights;

import box2dLight.Light;
import box2dLight.PointLight;
import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.view.Physics;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

/**
 * Created by grantammons on 6/27/15.
 */
public class LightView implements Lights {
    protected Light light;
    protected Physics physics;
    protected me.grantammons.rogueEngine.core.entities.items.Lights.Light lightModel;
    protected float redColor;
    protected float greenColor;
    protected float blueColor;
    private int counter = 0;

    public LightView(me.grantammons.rogueEngine.core.entities.items.Lights.Light light, Physics physics) {
        this.lightModel = light;
        this.light = new PointLight(physics.getRayHandler(), 128, null, lightModel.getBrightness(), 0f, 0f);
        this.physics = physics;
    }

    public void draw() {
        light.setPosition(getX(), getY());
        if (lightModel.isFlickering())
            setIntensity();
    }


    public void drawAt(float x, float y) {
        light.setPosition(x + (PIXEL_WIDTH / 2), y + (PIXEL_HEIGHT / 2));
        if (lightModel.isFlickering())
            setIntensity();
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
}