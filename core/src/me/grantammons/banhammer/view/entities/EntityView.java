package me.grantammons.banhammer.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.TimeUtils;
import me.grantammons.banhammer.core.Game;
import me.grantammons.banhammer.core.entities.Entity;

import static me.grantammons.banhammer.core.Constants.*;

/**
 * Created by grantammons on 6/5/15.
 */
public class EntityView {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;
    float WALK_SPEED = 90f;

    private long timestamp;
    protected boolean isWalking;

    public Sprite sprite;
    protected Game game;
    protected Entity entity;

    private int lastSeenX;
    private int lastSeenY;

    public EntityView(Game game, Entity entity, String asset) {
        this.game = game;
        this.entity = entity;
        sprite = new Sprite(new Texture(asset));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
        sprite.setPosition(entity.getX() * PIXEL_HEIGHT, entity.getY() * PIXEL_HEIGHT);
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

            switch (entity.getIntendedDirection()) {
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
            batch.draw(sprite, entity.getX() * PIXEL_WIDTH, entity.getY() * PIXEL_HEIGHT);
        }
    }

    private void setLastSeen() {
        lastSeenX = entity.getX();
        lastSeenY = entity.getY();
    }

    private boolean didLocationChange() {
        return lastSeenX != entity.getX() || lastSeenY != entity.getY();
    }
}
