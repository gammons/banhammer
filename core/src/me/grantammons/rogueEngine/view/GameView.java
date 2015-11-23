package me.grantammons.rogueEngine.view;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.core.entities.items.Lights.Light;
import me.grantammons.rogueEngine.core.entities.items.Lights.TorchLight;
import me.grantammons.rogueEngine.core.entities.items.props.Prop;
import me.grantammons.rogueEngine.core.entities.items.props.Torch;
import me.grantammons.rogueEngine.core.utils.los.Line;
import me.grantammons.rogueEngine.view.entities.AnimatedEntityView;
import me.grantammons.rogueEngine.view.entities.EntityView;
import me.grantammons.rogueEngine.view.entities.PlayerView;
import me.grantammons.rogueEngine.view.input.GameInputProcessor;
import me.grantammons.rogueEngine.view.input.InputListener;
import me.grantammons.rogueEngine.view.items.ItemView;
import me.grantammons.rogueEngine.view.items.lights.LightView;
import me.grantammons.rogueEngine.view.items.lights.TorchLightView;
import me.grantammons.rogueEngine.view.items.props.TorchView;
import me.grantammons.rogueEngine.view.map.MapView;
import me.grantammons.rogueEngine.view.tweens.CamZoomTween;
import me.grantammons.rogueEngine.view.tweens.ProgressBarTween;
import me.grantammons.rogueEngine.view.tweens.SpriteTween;
import me.grantammons.rogueEngine.view.utils.ProgressBar;

import java.util.ArrayList;

import static me.grantammons.rogueEngine.core.Constants.VIEWPORT_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.VIEWPORT_WIDTH;

public class GameView implements Screen, InputListener {
    private final Stage stage;
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
    private FovRenderer fovRenderer;
    private GamePopupMenu popupMenu;

    public GameView(GameInputProcessor processor) {
        entityViews = new ArrayList<>();
        lightViews = new ArrayList<>();
        shapeRenderer = new ShapeRenderer();

        inputProcessor = processor;
        game = new Game();
        hud = new Hud();
        physics = new Physics();
        Tween.registerAccessor(Sprite.class, new SpriteTween());
        Tween.registerAccessor(ProgressBar.class, new ProgressBarTween());
        Tween.registerAccessor(OrthographicCamera.class, new CamZoomTween());
        tweenManager = new TweenManager();

        stage = new Stage(new ScreenViewport());
        popupMenu = new GamePopupMenu(stage);

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
        inputProcessor.addListener(popupMenu);
        inputProcessor.addListener(this);

        batch.setProjectionMatrix(cam.combined);

        fovRenderer = new FovRenderer(cam, game.player, game.map);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        /*
        render lighted level
        render areas player can't currently see as black
        render visited areas that are not visible
        render player
        render monsters
         */

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        drawSprites(delta);

        lerpCameraToTarget();

        renderLights();
        mouseSelector.draw(batch, tweenManager);
        batch.end();

        batch.begin();
        for (EntityView entityView : entityViews) {
            if (entityView instanceof AnimatedEntityView) {
                ((AnimatedEntityView) entityView).drawHud(batch, tweenManager);
            }
        }
        fovRenderer.draw(batch, tweenManager);
        batch.end();

        popupMenu.render();

        hud.render(game);
    }

    private void renderLights() {
        for (LightView lightView : lightViews) {
            lightView.setVisible(lightInSight(lightView.getLightModel()));
            lightView.draw(batch, tweenManager);
        }

        physics.update(cam);
    }

    private boolean lightInSight(Light light) {
        Line line = new Line(game.map);
        return line.hasClearLine(light.location, game.player.location);
    }

    private void drawSprites(float delta) {
        mapView.draw(batch);
        for (EntityView entityView : entityViews) {
            entityView.updateDelta(delta);
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

    public InputProcessor getUIInputProcessor() {
        return stage;
    }

    @Override
    public void notify(int direction) {

    }

    @Override
    public void notifyMouseMoved(int screenX, int screenY) {

    }

    @Override
    public void notifyMouseClicked(int screenX, int screenY, int button) {

    }

    @Override
    public void notifyMouseScrollDown() {
        Tween.to(cam, 0, 0.2f).target(cam.viewportWidth / 0.5f, cam.viewportHeight / 0.5f).ease(Quad.INOUT).start(tweenManager);
    }

    @Override
    public void notifyMouseScrollUp() {
        Tween.to(cam, 0, 0.2f).target(cam.viewportWidth * 0.5f, cam.viewportHeight * 0.5f).ease(Quad.INOUT).start(tweenManager);
    }
}
