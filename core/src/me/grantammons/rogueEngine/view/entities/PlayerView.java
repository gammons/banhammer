package me.grantammons.rogueEngine.view.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.view.Physics;
import me.grantammons.rogueEngine.view.input.InputListener;
import me.grantammons.rogueEngine.view.items.lights.TorchLight;

public class PlayerView extends AnimatedEntityView implements InputListener {

    private Physics physics;
    private TorchLight light;

    public PlayerView(Game game, Physics physics) {
        super(game, game.player, "player.png");
        this.physics = physics;
        light = new TorchLight(sprite, physics);
    }

    public void draw(Batch batch) {
        super.draw(batch);
        light.draw();
    }

    @Override
    public void notify(int direction) {
        if (isWalking) return;

        if (game.playerCanMoveTo(direction)) {
            animatedEntity.intendedDirection = direction;
            game.tick();
        }
    }
}
