package me.grantammons.banhammer.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import me.grantammons.banhammer.core.Game;

/**
 * Created by grantammons on 5/30/15.
 */
public class PlayerView implements InputListener {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;
    float WALK_SPEED = 250f;

    public Sprite sprite;
    private Game game;
    private boolean isWalking = false;
    private long timestamp;
    private String walkDirection;
    private int origX;
    private int origY;



    public PlayerView(Game game) {
        this.game = game;
        sprite = new Sprite(new Texture("player.png"));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    @Override
    public void notify(int direction) {
        isWalking = true;
        origX = game.player.x;
        origY = game.player.y;

        switch (direction) {
            case Input.Keys.UP:
                walkDirection = "up";
                game.movePlayerUp();
                break;
            case Input.Keys.DOWN:
                walkDirection = "down";
                game.movePlayerDown();
                break;
            case Input.Keys.LEFT:
                walkDirection = "left";
                game.movePlayerLeft();
                break;
            case Input.Keys.RIGHT:
                walkDirection = "right";
                game.movePlayerRight();
                break;
        }

    }


    public void draw(Batch batch, float delta) {

        if (isWalking) {
            if (timestamp == 0) {
                timestamp = TimeUtils.millis();
            }

            //float lerped = MathUtils.lerp(0, 15, TimeUtils.timeSinceMillis(timestamp) / WALK_SPEEDj);
            float lerped = new Interpolation.Swing(1).apply(0f, 15f, TimeUtils.timeSinceMillis(timestamp) / WALK_SPEED);

            switch (walkDirection) {
                case "up":
                    sprite.setPosition(origX * PIXEL_HEIGHT, origY * PIXEL_HEIGHT + lerped);
                    break;
                case "down":
                    sprite.setPosition(origX * PIXEL_HEIGHT, origY * PIXEL_HEIGHT - lerped);
                    break;
                case "left":
                    sprite.setPosition(origX * PIXEL_HEIGHT - lerped, origY * PIXEL_HEIGHT);
                    break;
                case "right":
                    sprite.setPosition(origX * PIXEL_HEIGHT + lerped, origY * PIXEL_HEIGHT);
                    break;
            }
            sprite.draw(batch);

            if (lerped > 15) {
                isWalking = false;
                timestamp = 0;
            }
        } else {
            batch.draw(sprite, game.player.x * PIXEL_WIDTH, game.player.y * PIXEL_HEIGHT);
        }

    }
}
