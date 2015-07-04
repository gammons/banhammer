package me.grantammons.rogueEngine.view.items.lights;

import box2dLight.RayHandler;
import me.grantammons.rogueEngine.core.entities.items.Lights.Light;

public class TorchLightView extends LightView {

    public TorchLightView(Light light, RayHandler handler) {
        super(light, handler);
        setupLight();
    }

    private void setupLight() {
        this.redColor = 1f;
        this.greenColor = 0.6f;
        this.blueColor = 0.3f;
    }
}
