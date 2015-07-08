package me.grantammons.rogueEngine.view.entities;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.Lights.TorchLight;
import me.grantammons.rogueEngine.view.Physics;
import me.grantammons.rogueEngine.view.input.InputListener;
import me.grantammons.rogueEngine.view.items.lights.TorchLightView;

public class PlayerView extends AnimatedEntityView implements InputListener {

    private Physics physics;
    private TorchLightView light;

    public PlayerView(Game game, Physics physics) {
        super(game, game.player, "player.png");
        this.physics = physics;
        TorchLight t = new TorchLight(new Notifier());
        light = new TorchLightView(t, physics.getRayHandler());
    }

    public void draw(Batch batch, TweenManager tweenManager) {
        super.draw(batch, tweenManager);
        light.drawAt(sprite.getX(), sprite.getY());
    }

    @Override
    public void notify(int direction) {
        if (game.playerCanMoveTo(direction)) {
            animatedEntity.intendedLocation = Location.setLocationFromDirection(animatedEntity.location, direction);
            game.tick();
        }
    }

    @Override
    public void notifyMouseMoved(int screenX, int screenY) {

    }

    @Override
    public void notifyMouseClicked(int screenX, int screenY, int button) {

    }
}
