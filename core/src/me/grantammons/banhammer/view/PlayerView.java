package me.grantammons.banhammer.view;

import com.badlogic.gdx.Input;
import me.grantammons.banhammer.core.Game;

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

        entity.intendedDirection = direction;
        game.tick();

        origX = entity.x;
        origY = entity.y;

        switch (direction) {
            case Input.Keys.UP:
                if (game.canPlayerMove(UP))
                    walk(UP);
                break;
            case Input.Keys.DOWN:
                if (game.canPlayerMove(DOWN))
                    walk(DOWN);
                break;
            case Input.Keys.LEFT:
                if (game.canPlayerMove(LEFT))
                    walk(LEFT);
                break;
            case Input.Keys.RIGHT:
                if (game.canPlayerMove(RIGHT))
                    walk(RIGHT);
                break;
        }

    }

    private void walk(int direction) {
        walkDirection = direction;
        isWalking = true;
        game.movePlayer(direction);
    }

}
