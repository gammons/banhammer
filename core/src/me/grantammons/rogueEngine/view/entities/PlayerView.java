package me.grantammons.rogueEngine.view.entities;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.view.Physics;
import me.grantammons.rogueEngine.view.input.InputListener;

public class PlayerView extends EntityView implements InputListener {

    private Physics physics;
    private PointLight light;
    private int counter = 0;

    public PlayerView(Game game, Physics physics) {
        super(game, game.player, "player.png");
        this.physics = physics;
        addLight();

    }

    private void addLight() {
        light = new PointLight(physics.getRayHandler(), 128, null, 64f, 0f, 0f);
        //light.setPosition(sprite.getX() + (Constants.PIXEL_WIDTH / 2), sprite.getY() + (Constants.PIXEL_HEIGHT / 2));
    }

    public void draw(Batch batch) {
        super.draw(batch);
        counter ++;
        if (counter % 5 == 0) {
            float intensity = MathUtils.randomTriangular(0.8f, 0.85f);
            light.setColor(1f, 0.6f, 0.3f, intensity);
            light.setDistance(MathUtils.randomTriangular(124f, 128f));
            counter = 0;
        }
        light.setPosition(sprite.getX() + (Constants.PIXEL_WIDTH / 2), sprite.getY() + (Constants.PIXEL_HEIGHT / 2));
    }

    @Override
    public void notify(int direction) {
        if (isWalking) return;

        if (game.playerCanMoveTo(direction)) {
            entity.intendedDirection = direction;
            game.tick();
        }
    }
}
