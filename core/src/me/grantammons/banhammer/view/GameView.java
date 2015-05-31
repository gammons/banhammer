package me.grantammons.banhammer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by grantammons on 5/30/15.
 */
public class GameView implements Screen {
    private SpriteBatch batch;
    private PlayerView playerView;
    private OrthographicCamera cam;

    int WORLD_WIDTH = 256;
    int WORLD_HEIGHT = 256;

    @Override
    public void show() {
        batch = new SpriteBatch();
        playerView = new PlayerView();

        setupCamera();
        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        playerView.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void setupCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }
}
