package me.grantammons.rogueEngine.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

import static me.grantammons.rogueEngine.core.Constants.*;

/**
 * Created by grantammons on 6/5/15.
 */
public class AnimatedEntityView {
    float WALK_SPEED = 90f;

    private long timestamp;
    protected boolean isWalking;

    public Sprite sprite;
    protected Game game;
    protected AnimatedEntity animatedEntity;

    private int lastSeenX;
    private int lastSeenY;

    public AnimatedEntityView(Game game, AnimatedEntity animatedEntity, String asset) {
        this.game = game;
        this.animatedEntity = animatedEntity;
        sprite = new Sprite(new Texture(asset));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
        sprite.setPosition(animatedEntity.location.x * PIXEL_WIDTH, animatedEntity.location.y * PIXEL_HEIGHT);
        isWalking = false;
    }

    public void draw(Batch batch) {
        if (didLocationChange()) {
            isWalking = true;
            if (timestamp == 0) {
                timestamp = TimeUtils.millis();
            }

            float t = TimeUtils.timeSinceMillis(timestamp) / WALK_SPEED;
            float lerped = Interpolation.fade.apply(0f, 15f, t);

            switch (animatedEntity.intendedDirection) {
                case NORTH:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT, lastSeenY * PIXEL_HEIGHT + lerped);
                    break;
                case SOUTH:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT, lastSeenY * PIXEL_HEIGHT - lerped);
                    break;
                case WEST:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT - lerped, lastSeenY * PIXEL_HEIGHT);
                    break;
                case EAST:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT + lerped, lastSeenY * PIXEL_HEIGHT);
                    break;
                case NORTHEAST:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT + lerped, lastSeenY * PIXEL_HEIGHT + lerped);
                    break;
                case NORTHWEST:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT - lerped, lastSeenY * PIXEL_HEIGHT + lerped);
                    break;
                case SOUTHEAST:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT + lerped, lastSeenY * PIXEL_HEIGHT - lerped);
                    break;
                case SOUTHWEST:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT - lerped, lastSeenY * PIXEL_HEIGHT - lerped);
                    break;
            }
            sprite.draw(batch);

            if (lerped >= 15) {
                setLastSeen();
                timestamp = 0;
                isWalking = false;
            }
        } else {
            batch.draw(sprite, animatedEntity.location.x * PIXEL_WIDTH, animatedEntity.location.y * PIXEL_HEIGHT);
        }
    }

    private void setLastSeen() {
        lastSeenX = animatedEntity.location.x;
        lastSeenY = animatedEntity.location.y;
    }

    private boolean didLocationChange() {
        return lastSeenX != animatedEntity.location.x || lastSeenY != animatedEntity.location.y;
    }

    public AnimatedEntity getAnimatedEntity() {
        return animatedEntity;
    }
}
