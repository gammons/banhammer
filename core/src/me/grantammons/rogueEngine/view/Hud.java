package me.grantammons.rogueEngine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;

/**
 * Created by grantammons on 6/6/15.
 */
public class Hud {

    private Sprite hudScaffold;
    private OrthographicCamera hudCamera;
    private BitmapFont font;
    private SpriteBatch batch;

    public Hud() {
        batch = new SpriteBatch();
        setupHudScaffold();
        setupFont();
        setupCamera();
    }


    public void resize (int width, int height) {
        hudCamera.viewportHeight = Constants.VIEWPORT_HEIGHT * 2;
        hudCamera.viewportWidth = ((Constants.VIEWPORT_HEIGHT * 2) / (float)height) * (float)width;
//        hudCamera.viewportHeight = 375;
//        hudCamera.viewportWidth = 667;
        hudCamera.position.set(hudCamera.viewportWidth / 4, hudCamera.viewportHeight / 4, 0);
        hudCamera.update();
    }

    public void render(Game game) {
        batch.setProjectionMatrix(hudCamera.combined);
        hudCamera.update();

        batch.begin();
        hudScaffold.draw(batch);

        int i = 0;
        for(String s : game.recentNotifications()) {
            font.draw(batch, s, 340, 290 + i);
            i += 20;
        }

        batch.end();
    }

    private void setupCamera() {
        hudCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH * 2, Constants.VIEWPORT_HEIGHT * 2);
        hudCamera.position.set(hudCamera.viewportWidth / 4, hudCamera.viewportHeight / 4, 0);
        //hudCamera.position.set(0,0,0);
        hudCamera.setToOrtho(true);
        hudCamera.update();
    }

    private void setupFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Inconsolata-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.flip = true;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator.dispose();
    }

    private void setupHudScaffold() {
        hudScaffold = new Sprite(new Texture("hud.png"));
        hudScaffold.flip(false, true);
        hudScaffold.setPosition(0,285);
    }

}
