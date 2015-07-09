package me.grantammons.rogueEngine.view;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.core.entities.items.Lights.Light;
import me.grantammons.rogueEngine.core.entities.items.Lights.TorchLight;
import me.grantammons.rogueEngine.core.entities.items.props.Prop;
import me.grantammons.rogueEngine.core.entities.items.props.Torch;
import me.grantammons.rogueEngine.view.entities.AnimatedEntityView;
import me.grantammons.rogueEngine.view.entities.EntityView;
import me.grantammons.rogueEngine.view.entities.PlayerView;
import me.grantammons.rogueEngine.view.input.GameInputProcessor;
import me.grantammons.rogueEngine.view.items.ItemView;
import me.grantammons.rogueEngine.view.items.lights.LightView;
import me.grantammons.rogueEngine.view.items.lights.TorchLightView;
import me.grantammons.rogueEngine.view.items.props.TorchView;
import me.grantammons.rogueEngine.view.map.MapView;

import java.util.ArrayList;

import static me.grantammons.rogueEngine.core.Constants.*;

public class GameView implements Screen {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private GameInputProcessor inputProcessor;

    private MapView mapView;
    private PlayerView playerView;
    private ArrayList<EntityView> entityViews;
    private ArrayList<LightView> lightViews;

    private Game game;
    private Hud hud;
    private Physics physics;
    private ShapeRenderer shapeRenderer;
    private MouseSelector mouseSelector;
    private TweenManager tweenManager;


    public GameView(GameInputProcessor processor) {
        entityViews = new ArrayList<>();
        lightViews = new ArrayList<>();
        shapeRenderer = new ShapeRenderer();

        inputProcessor = processor;
        game = new Game();
        hud = new Hud();
        physics = new Physics();
        Tween.registerAccessor(Sprite.class, new SpriteTween());
        tweenManager = new TweenManager();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        physics.init();

        mapView = new MapView(game.map, physics);
        playerView = new PlayerView(game, physics);
        entityViews.add(playerView);
        inputProcessor.addListener(playerView);

        setupCamera();
        setupEntityViews();
        mouseSelector = new MouseSelector(cam, game);
        inputProcessor.addListener(mouseSelector);

        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        expireDeadThings();
        tweenManager.update(delta);

        /*
        render lighted level
        render areas player can't currently see as black
        render visited areas that are not visible
        render player
        render monsters
         */

        shapeRenderer.setProjectionMatrix(cam.combined);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        drawSprites();

        lerpCameraToTarget();

        renderLights();
        renderFov();
        mouseSelector.draw(batch, tweenManager);
        batch.end();

        batch.begin();
        renderSeenLevel();
        batch.end();
        hud.render(game);
    }

    /*
    For tiles that are outside of the FOV area, and are also seen by the player,
    render them darkened and unlit.
     */
    private void renderSeenLevel() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        batch.setColor(0.2f,0.2f,0.2f,0.8f);
        for (Location location : game.player.getVisitedTiles()) {
            if (!game.player.getVisibleTiles().contains(location))
                mapView.drawMapTile(batch, location.x, location.y);
        }

        batch.setColor(Color.WHITE);
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void renderLights() {
        for (LightView lightView : lightViews) {
            lightView.draw(batch, tweenManager);
        }

        physics.update(cam);
    }

    private void renderFov() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1f);
        for(int x = 0; x <= VIEWPORT_WIDTH / PIXEL_WIDTH; x++) {
            for(int y = 0; y <= VIEWPORT_HEIGHT / PIXEL_HEIGHT; y ++) {
                Location l = new Location(x,y);
                if (!game.player.getVisitedTiles().contains(l))
                    shapeRenderer.rect(x * PIXEL_WIDTH, y * PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT);
            }
        }

        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawSprites() {
        mapView.draw(batch);
        for (EntityView entityView : entityViews) {
            entityView.draw(batch, tweenManager);
        }
    }

    private void lerpCameraToTarget() {
        Vector3 position = cam.position;
        position.x = cam.position.x + (playerView.getSprite().getX() - cam.position.x) * .2f;
        position.y = cam.position.y + (playerView.getSprite().getY() - cam.position.y) * .2f;
        cam.position.set(position);
        cam.update();
    }

    private void expireDeadThings() {
        entityViews.removeIf(m -> m.getEntity().isExpired() == true);
    }

    @Override
    public void resize(int width, int height) {
        hud.resize(width, height);
        cam.viewportHeight = VIEWPORT_HEIGHT;
        cam.viewportWidth = (VIEWPORT_WIDTH / (float)height) * width;
        cam.position.set(playerView.getSprite().getX(), playerView.getSprite().getY(), 0);
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
        cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT * (h / w));
        cam.position.set(playerView.getSprite().getX(), playerView.getSprite().getY(), 0);
        cam.update();
    }

    private void setupEntityViews() {
        for(AnimatedEntity e : game.map.getMonsters()) {
            entityViews.add(new AnimatedEntityView(game, e, "monster.png"));
        }
        for(Item i : game.getItems()) {
            entityViews.add(new ItemView(game, i));
        }
        for (Prop prop : game.map.getProps()) {
            if (prop instanceof Torch) {
                entityViews.add(new TorchView(game, prop));
            }
        }
        for (Light light : game.map.getLights()) {
            if (light instanceof TorchLight) {
                lightViews.add(new TorchLightView(light, physics.getRayHandler()));
            }
        }
    }
}
