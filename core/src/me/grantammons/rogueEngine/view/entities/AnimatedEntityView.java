package me.grantammons.rogueEngine.view.entities;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.view.Drawable;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class AnimatedEntityView implements Drawable{
    float WALK_SPEED = 0.17f;

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
    }

    public void draw(Batch batch, TweenManager tweenManager) {
        if (didLocationChange()) {
            Tween.to(sprite, 0, WALK_SPEED).target(animatedEntity.location.x * PIXEL_WIDTH, animatedEntity.location.y * PIXEL_HEIGHT).ease(Quad.INOUT).start(tweenManager);
            setLastSeen();
        }
        sprite.draw(batch);
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
