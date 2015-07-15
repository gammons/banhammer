package me.grantammons.rogueEngine.view.entities;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.rogueEngine.view.Drawable;

public class EntityView implements Drawable {
    protected Sprite sprite;
    protected Game game;
    protected Entity entity;

    public EntityView(Game game, Entity entity) {
        this.entity = entity;
        this.game = game;
    }

    public void draw(Batch batch, TweenManager tweenManager) {
        if (entity.isExpired()) return;
        sprite.setPosition(entity.location.x * Constants.PIXEL_WIDTH, entity.location.y * Constants.PIXEL_HEIGHT);
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Entity getEntity() {
        return entity;
    }
}
