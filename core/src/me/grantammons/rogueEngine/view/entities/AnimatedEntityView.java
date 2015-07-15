package me.grantammons.rogueEngine.view.entities;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.view.utils.ProgressBar;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class AnimatedEntityView extends EntityView {
    float WALK_SPEED = 0.17f;

    private int lastSeenX;
    private int lastSeenY;
    private ProgressBar hpBar;

    public AnimatedEntityView(Game game, AnimatedEntity animatedEntity, String asset) {
        super(game, animatedEntity);
        sprite = new Sprite(new Texture(asset));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
        sprite.setPosition(animatedEntity.location.x * PIXEL_WIDTH, animatedEntity.location.y * PIXEL_HEIGHT);
        hpBar = new ProgressBar(0,
                animatedEntity.getMaxHp(),
                animatedEntity.getHp(),
                animatedEntity.location.x * PIXEL_WIDTH,
                animatedEntity.location.y * PIXEL_HEIGHT - ProgressBar.HEIGHT);

    }

    @Override
    public void draw(Batch batch, TweenManager tweenManager) {
        hpBar.setProjectionMatrix(batch.getProjectionMatrix());

        if (didLocationChange()) {
            Tween.to(sprite, 0, WALK_SPEED).target(entity.location.x * PIXEL_WIDTH, entity.location.y * PIXEL_HEIGHT).ease(Quad.INOUT).start(tweenManager);
            Tween.to(hpBar, 0, WALK_SPEED).target(entity.location.x * PIXEL_WIDTH, entity.location.y * PIXEL_HEIGHT - 3).ease(Quad.INOUT).start(tweenManager);
            setLastSeen();
        }
        sprite.draw(batch);
        hpBar.update(((AnimatedEntity)entity).getHp());
    }

    public void drawHud(Batch batch, TweenManager tweenManager) {
        hpBar.draw(batch, tweenManager);
    }

    private void setLastSeen() {
        lastSeenX = entity.location.x;
        lastSeenY = entity.location.y;
    }

    private boolean didLocationChange() {
        return lastSeenX != entity.location.x || lastSeenY != entity.location.y;
    }
}
