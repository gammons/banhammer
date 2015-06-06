package me.grantammons.banhammer.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import me.grantammons.banhammer.core.entities.Entity;
import me.grantammons.banhammer.core.Game;
import static me.grantammons.banhammer.core.Constants.*;

/**
 * Created by grantammons on 6/5/15.
 */
public class EntityView {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;
    float WALK_SPEED = 150f;

    protected boolean isWalking = false;
    private long timestamp;
    protected int walkDirection;
    protected int origX;
    protected int origY;

    public Sprite sprite;
    protected Game game;
    protected Entity entity;

    public EntityView(Game game, Entity entity, String asset) {
        this.game = game;
        this.entity = entity;
        sprite = new Sprite(new Texture(asset));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
        sprite.setPosition(entity.x * PIXEL_HEIGHT, entity.y * PIXEL_HEIGHT);
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
            batch.draw(sprite, entity.x * PIXEL_WIDTH, entity.y * PIXEL_HEIGHT);
        }
    }

}
