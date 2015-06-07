package me.grantammons.banhammer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.grantammons.banhammer.core.Constants;

/**
 * Created by grantammons on 6/6/15.
 */
public class Hud {

    private OrthographicCamera hudCamera;
    private BitmapFont font;
    private SpriteBatch batch;

    public Hud() {
        batch = new SpriteBatch();
        font = new BitmapFont( Gdx.files.internal("arial-15.fnt"), true);
        //font.getRegion().getTexture().setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setupCamera();
    }


    public void resize (int width, int height) {
        hudCamera.viewportHeight = Constants.VIEWPORT_HEIGHT;
        hudCamera.viewportWidth = (Constants.VIEWPORT_HEIGHT / (float)height) * (float)width;
        hudCamera.position.set(hudCamera.viewportWidth / 2, hudCamera.viewportHeight / 2, 0);
        hudCamera.update();
    }

    public void render() {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        font.draw(batch, "testing shit", 10,0);
        batch.end();
    }

    private void setupCamera() {
        hudCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        hudCamera.position.set(0,0,0);
        hudCamera.setToOrtho(true);
        hudCamera.update();
    }

}
