package me.grantammons.rogueEngine.view.items.props;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.items.props.Prop;
import me.grantammons.rogueEngine.view.items.ItemView;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class TorchView extends ItemView {
    private ParticleEffect particleEffect;

    public TorchView(Game game, Prop item) {
        super(game, item);
        sprite.setPosition(item.location.x * PIXEL_WIDTH, item.location.y * PIXEL_HEIGHT);
        setupParticles();
    }

    private void setupParticles() {
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("flame.p"), Gdx.files.internal(""));
        particleEffect.scaleEffect(0.2f);
        particleEffect.start();
    }


    @Override
    public void draw(Batch batch, TweenManager tweenManager) {
        super.draw(batch, tweenManager);
        particleEffect.setPosition(xPos(), yPos());
        particleEffect.update(delta);
        particleEffect.draw(batch);

    }

    public float xPos() {
        return entity.location.x * PIXEL_WIDTH + (PIXEL_WIDTH / 2) - 1.5f;
    }

    public int yPos() {
        return entity.location.y * PIXEL_WIDTH + (PIXEL_WIDTH / 2);

    }
}
