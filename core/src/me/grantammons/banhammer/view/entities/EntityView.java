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
    float WALK_SPEED = 150f;

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
        sprite.setPosition(entity.x * PIXEL_HEIGHT, entity.y * PIXEL_HEIGHT);
        isWalking = false;
    }

    public void draw(Batch batch) {
        if (didLocationChange()) {
            if (timestamp == 0) {
                timestamp = TimeUtils.millis();
            }

            float t = TimeUtils.timeSinceMillis(timestamp) / WALK_SPEED;
            float lerped = Interpolation.fade.apply(0f, 15f, t);

            switch (entity.intendedDirection) {
                case UP:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT, lastSeenY * PIXEL_HEIGHT + lerped);
                    break;
                case DOWN:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT, lastSeenY * PIXEL_HEIGHT - lerped);
                    break;
                case LEFT:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT - lerped, lastSeenY * PIXEL_HEIGHT);
                    break;
                case RIGHT:
                    sprite.setPosition(lastSeenX * PIXEL_HEIGHT + lerped, lastSeenY * PIXEL_HEIGHT);
                    break;
            }
            sprite.draw(batch);

            if (lerped >= 15) {
                setLastSeen();
                timestamp = 0;
            }
        } else {
            batch.draw(sprite, entity.x * PIXEL_WIDTH, entity.y * PIXEL_HEIGHT);
        }
    }

    private void setLastSeen() {
        lastSeenX = entity.x;
        lastSeenY = entity.y;
    }

    private boolean didLocationChange() {
        return lastSeenX != entity.x || lastSeenY != entity.y;
    }
}
