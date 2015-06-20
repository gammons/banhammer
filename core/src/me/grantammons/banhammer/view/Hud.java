package me.grantammons.banhammer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import me.grantammons.banhammer.core.Constants;
import me.grantammons.banhammer.core.Game;

/**
 * Created by grantammons on 6/6/15.
 */
public class Hud {

    private OrthographicCamera hudCamera;
    private BitmapFont font;
    private SpriteBatch batch;

    public Hud() {
        batch = new SpriteBatch();
        //font = new BitmapFont( Gdx.files.internal("arial-15.fnt"), true);
        //font.getRegion().getTexture().setFilter( Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setupFont();
        setupCamera();
    }


    public void resize (int width, int height) {
        hudCamera.viewportHeight = Constants.VIEWPORT_HEIGHT;
        hudCamera.viewportWidth = (Constants.VIEWPORT_HEIGHT / (float)height) * (float)width;
        hudCamera.position.set(hudCamera.viewportWidth / 2, hudCamera.viewportHeight / 2, 0);
        hudCamera.update();
    }

    public void render(Game game) {
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
        int i = 0;
        for(String s : game.recentNotifications()) {
            font.draw(batch, s, 10,i);
            i += 10;
        }
        batch.end();
    }

    private void setupCamera() {
        hudCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        hudCamera.position.set(0,0,0);
        hudCamera.setToOrtho(true);
        hudCamera.update();
    }

    private void setupFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Inconsolata-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 14;
        parameter.flip = true;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

}
