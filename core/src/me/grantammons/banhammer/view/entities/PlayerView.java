package me.grantammons.banhammer.view.entities;

import com.badlogic.gdx.Input;
import me.grantammons.banhammer.core.Constants;
import me.grantammons.banhammer.core.Game;
import me.grantammons.banhammer.view.input.InputListener;

import static me.grantammons.banhammer.core.Constants.*;

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

        setIntendedDirection(direction);
        if (game.canMoveTo(entity, entity.intendedDirection)) {
            System.out.println("GAME TICK");
            game.tick();
        } else {
            entity.intendedDirection = Constants.NO_DIRECTION;
        }
    }

    private void setIntendedDirection(int direction) {
        switch (direction) {
            case Input.Keys.UP: entity.intendedDirection = UP; break;
            case Input.Keys.DOWN: entity.intendedDirection = DOWN; break;
            case Input.Keys.LEFT: entity.intendedDirection = LEFT; break;
            case Input.Keys.RIGHT: entity.intendedDirection = RIGHT; break;
        }
    }

}
