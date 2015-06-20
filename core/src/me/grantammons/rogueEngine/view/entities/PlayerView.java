package me.grantammons.rogueEngine.view.entities;

import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.view.input.InputListener;

/**
 * Created by grantammons on 5/30/15.
 */
public class PlayerView extends EntityView implements InputListener {

    public PlayerView(Game game) {
        super(game, game.player, "player.png");
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
