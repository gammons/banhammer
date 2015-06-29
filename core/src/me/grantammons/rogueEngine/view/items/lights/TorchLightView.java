package me.grantammons.rogueEngine.view.items.lights;

import me.grantammons.rogueEngine.core.entities.items.Lights.Light;
import me.grantammons.rogueEngine.view.Physics;

public class TorchLightView extends LightView {

    public TorchLightView(Light light, Physics physics) {
        super(light, physics);
        setupLight();
    }

    private void setupLight() {
        this.redColor = 1f;
        this.greenColor = 0.6f;
        this.blueColor = 0.3f;
    }
}
