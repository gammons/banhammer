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
    private GameInputProcessor inputProcessor;

    int WORLD_WIDTH = 64;
    int WORLD_HEIGHT = 64;

    public GameView(GameInputProcessor processor) {
        inputProcessor = processor;
    }

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

        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        playerView.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = WORLD_HEIGHT;
        cam.viewportWidth = (WORLD_HEIGHT / (float)height) * width;
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.update();
        //System.out.println("new size = ("+width+", "+height+")");
        //System.out.println("viewport = ("+cam.viewportWidth+", "+cam.viewportHeight+")");
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
