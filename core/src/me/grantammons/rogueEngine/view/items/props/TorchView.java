package me.grantammons.rogueEngine.view.items.props;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.view.Physics;
import me.grantammons.rogueEngine.view.items.ItemView;
import me.grantammons.rogueEngine.view.items.lights.TorchLight;

public class TorchView extends ItemView {
    private TextureRegion[] regions = new TextureRegion[4];
    private Animation animation;
    private float stateTime;
    private Physics physics;
    private TorchLight light;

    public TorchView(Game game, Item item, Physics physics) {
        super(game, item);
        this.physics = physics;
        setupTexture();
        setupLight();
        sprite.setPosition(item.location.x * Constants.PIXEL_WIDTH, item.location.y * Constants.PIXEL_HEIGHT);
    }


    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(stateTime, true), item.location.x * Constants.PIXEL_WIDTH, item.location.y * Constants.PIXEL_HEIGHT);
        light.draw();
    }

    private void setupTexture() {
        Texture torchTexture = new Texture("torch.png");
        regions[0] = new TextureRegion(torchTexture, 0, 0, 16, 16);
        regions[1] = new TextureRegion(torchTexture, 16, 0, 16, 16);
        regions[2] = new TextureRegion(torchTexture, 32, 0, 16, 16);
        regions[3] = new TextureRegion(torchTexture, 48, 0, 16, 16);
        animation = new Animation(0.25f, regions);
    }

    private void setupLight() {
        light = new TorchLight(sprite, physics);
    }

}
