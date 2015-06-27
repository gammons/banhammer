package me.grantammons.rogueEngine.view.items.lights;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.view.Physics;

public class TorchLight implements Lights {

    private Sprite sprite;
    private PointLight light;
    private int counter = 0;
    private Physics physics;

    public TorchLight(Sprite sprite, Physics physics) {
        this.physics = physics;
        this.sprite = sprite;
        light = new PointLight(physics.getRayHandler(), 128, null, 64f, 0f, 0f);

    }

    public void draw() {
        counter ++;
        if (counter % 5 == 0) {
            float intensity = MathUtils.randomTriangular(0.8f, 0.85f);
            light.setColor(1f, 0.6f, 0.3f, intensity);
            light.setDistance(MathUtils.randomTriangular(124f, 128f));
            counter = 0;
        }
        light.setPosition(sprite.getX() + (Constants.PIXEL_WIDTH / 2), sprite.getY() + (Constants.PIXEL_HEIGHT / 2));

    }
}
