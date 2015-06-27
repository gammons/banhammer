package me.grantammons.rogueEngine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.view.entities.AnimatedEntityView;
import me.grantammons.rogueEngine.view.entities.PlayerView;
import me.grantammons.rogueEngine.view.input.GameInputProcessor;
import me.grantammons.rogueEngine.view.items.ItemView;
import me.grantammons.rogueEngine.view.map.MapView;

import java.util.ArrayList;

public class GameView implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private GameInputProcessor inputProcessor;

    private MapView mapView;
    private PlayerView playerView;
    private ArrayList<AnimatedEntityView> monsterViews;
    private ArrayList<ItemView> itemViews;
    private Game game;
    private Hud hud;
    private float accumulator;
    private Physics physics;


    public GameView(GameInputProcessor processor) {
        monsterViews = new ArrayList<AnimatedEntityView>();
        itemViews = new ArrayList<ItemView>();
        inputProcessor = processor;
        game = new Game();
        hud = new Hud();
        physics = new Physics();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        physics.init();

        mapView = new MapView(game.map, physics);
        playerView = new PlayerView(game, physics);
        setupMonsters();
        setupItems();
        inputProcessor.addListener(playerView);

        setupCamera();
        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        expireDeadThings();

        batch.begin();

        mapView.draw(batch);
        playerView.draw(batch);

        renderMonstersAndItems();
        lerpCameraToTarget();

        batch.end();
        physics.update(cam);

        hud.render(game);
    }

    private void lerpCameraToTarget() {
        Vector3 position = cam.position;
        position.x = cam.position.x + (playerView.sprite.getX() - cam.position.x) * .2f;
        position.y = cam.position.y + (playerView.sprite.getY() - cam.position.y) * .2f;
        cam.position.set(position);
        cam.update();
    }

    private void expireDeadThings() {
        monsterViews.removeIf(m -> m.getAnimatedEntity().isExpired() == true);
    }

    private void renderMonstersAndItems() {
        for (AnimatedEntityView monsterView : monsterViews) {
            monsterView.draw(batch);
        }
        for (ItemView itemView : itemViews) {
            itemView.draw(batch);
        }
    }

    @Override
    public void resize(int width, int height) {
        hud.resize(width, height);
        cam.viewportHeight = Constants.VIEWPORT_HEIGHT;
        cam.viewportWidth = (Constants.VIEWPORT_WIDTH / (float)height) * width;
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
        cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT * (h / w));
        cam.position.set(playerView.sprite.getX(), playerView.sprite.getY(), 0);
        cam.update();
    }

    private void debugCoords(float x, float y) {
        System.out.println("viewport = ("+x+", "+y+")");
    }

    private void setupMonsters() {
        for(AnimatedEntity e : game.getMonsters()) {
            monsterViews.add(new AnimatedEntityView(game, e, "monster.png"));
        }
    }

    private void setupItems() {
        for(Item i : game.getItems()) {
            itemViews.add(new ItemView(game, i));
        }
    }
}
