package me.grantammons.banhammer.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.grantammons.banhammer.core.Game;

/**
 * Created by grantammons on 5/30/15.
 */
public class GameView implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private GameInputProcessor inputProcessor;

    private MapView mapView;
    private PlayerView playerView;
    private Game game;
    private boolean isWalking;

    int WORLD_WIDTH = 128;
    int WORLD_HEIGHT = 128;

    public GameView(GameInputProcessor processor) {
        inputProcessor = processor;
        game = new Game();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        mapView = new MapView(game.map);
        playerView = new PlayerView(game);
        inputProcessor.addListener(playerView);

        setupCamera();
        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);


        batch.begin();
        mapView.draw(batch);
        //batch.draw(playerView.sprite, playerView.sprite.getX() + lerped, playerView.sprite.getY());
        playerView.draw(batch, delta);
        cam.position.set(playerView.sprite.getX(), playerView.sprite.getY(), 0);
        cam.update();
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = WORLD_HEIGHT;
        cam.viewportWidth = (WORLD_HEIGHT / (float)height) * width;
        cam.position.set(playerView.sprite.getX(), playerView.sprite.getY(), 0);
        cam.update();
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
        batch.dispose();
    }

    private void setupCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT * (h / w));
        //cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    private void debugCoords(float x, float y) {
        System.out.println("viewport = ("+x+", "+y+")");
    }
}
