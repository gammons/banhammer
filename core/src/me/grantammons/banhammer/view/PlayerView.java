package me.grantammons.banhammer.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import me.grantammons.banhammer.core.Game;

import static me.grantammons.banhammer.core.Constants.*;

/**
 * Created by grantammons on 5/30/15.
 */
public class PlayerView implements InputListener {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;
    float WALK_SPEED = 150f;

    public Sprite sprite;
    private Game game;
    private boolean isWalking = false;
    private long timestamp;
    private int walkDirection;
    private int origX;
    private int origY;



    public PlayerView(Game game) {
        this.game = game;
        sprite = new Sprite(new Texture("player.png"));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    @Override
    public void notify(int direction) {
        if (isWalking) return;

        origX = game.player.x;
        origY = game.player.y;

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

    public void draw(Batch batch) {

        if (isWalking) {
            if (timestamp == 0) {
                timestamp = TimeUtils.millis();
            }

            float t = TimeUtils.timeSinceMillis(timestamp) / WALK_SPEED;
            float lerped = Interpolation.fade.apply(0f, 15f, t);

            switch (walkDirection) {
                case UP:
                    sprite.setPosition(origX * PIXEL_HEIGHT, origY * PIXEL_HEIGHT + lerped);
                    break;
                case DOWN:
                    sprite.setPosition(origX * PIXEL_HEIGHT, origY * PIXEL_HEIGHT - lerped);
                    break;
                case LEFT:
                    sprite.setPosition(origX * PIXEL_HEIGHT - lerped, origY * PIXEL_HEIGHT);
                    break;
                case RIGHT:
                    sprite.setPosition(origX * PIXEL_HEIGHT + lerped, origY * PIXEL_HEIGHT);
                    break;
            }
            sprite.draw(batch);

            if (lerped >= 15) {
                isWalking = false;
                timestamp = 0;
            }
        } else {
            batch.draw(sprite, game.player.x * PIXEL_WIDTH, game.player.y * PIXEL_HEIGHT);
        }
    }

    private void walk(int direction) {
        walkDirection = direction;
        isWalking = true;
        game.movePlayer(direction);
    }

}
