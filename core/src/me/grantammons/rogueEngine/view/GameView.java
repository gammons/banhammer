package me.grantammons.rogueEngine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private ArrayList<AnimatedEntityView> monsterViews;
    private ArrayList<ItemView> itemViews;
    private ArrayList<ItemView> propViews;
    private ArrayList<LightView> lightViews;
    private ArrayList<Location> nonVisibleTiles;

    private Game game;
    private Hud hud;
    private Physics physics;
    private ShapeRenderer shapeRenderer;


    public GameView(GameInputProcessor processor) {
        monsterViews = new ArrayList<>();
        itemViews = new ArrayList<>();
        propViews = new ArrayList<>();
        lightViews = new ArrayList<>();
        nonVisibleTiles = new ArrayList<>();
        shapeRenderer = new ShapeRenderer();

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
        setupProps();
        setupLights();
        inputProcessor.addListener(playerView);

        setupCamera();
        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        expireDeadThings();

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
        batch.end();

        lerpCameraToTarget();

        renderLights();
        renderFov();
        renderSeenLevel();
        hud.render(game);
    }

    /*
    For tiles that are outside of the FOV area, and are also seen by the player,
    render them darkened and unlit.
     */
    private void renderSeenLevel() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 0.5f);
        shapeRenderer.rect(64, 64, PIXEL_WIDTH, PIXEL_HEIGHT);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void renderLights() {
        physics.update(cam);
    }

    private void renderFov() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1f);
        nonVisibleTiles.clear();
        for(int x = 0; x <= VIEWPORT_WIDTH / PIXEL_WIDTH; x++) {
            for(int y = 0; y <= VIEWPORT_HEIGHT / PIXEL_HEIGHT; y ++) {
                Location l = new Location(x,y);
                if (!game.player.getVisibleTiles().contains(l)) {
                    nonVisibleTiles.add(l);
                    shapeRenderer.rect(x * PIXEL_WIDTH, y * PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT);
                }
            }
        }

        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawSprites() {
        mapView.draw(batch);
        playerView.draw(batch);

        renderMonstersAndItems();
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
        for (ItemView itemView : propViews) {
            itemView.draw(batch);
        }
        for (LightView lightView : lightViews) {
            lightView.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        hud.resize(width, height);
        cam.viewportHeight = VIEWPORT_HEIGHT;
        cam.viewportWidth = (VIEWPORT_WIDTH / (float)height) * width;
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
        cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT * (h / w));
        cam.position.set(playerView.sprite.getX(), playerView.sprite.getY(), 0);
        cam.update();
    }

    private void setupMonsters() {
        for(AnimatedEntity e : game.map.getMonsters()) {
            monsterViews.add(new AnimatedEntityView(game, e, "monster.png"));
        }
    }

    private void setupItems() {
        for(Item i : game.getItems()) {
            itemViews.add(new ItemView(game, i));
        }
    }

    private void setupProps() {
        for (Prop prop : game.map.getProps()) {
            if (prop instanceof Torch) {
                propViews.add(new TorchView(game, prop));
            }
        }
    }
    private void setupLights() {
        for (Light light : game.map.getLights()) {
            if (light instanceof TorchLight) {
                lightViews.add(new TorchLightView(light, physics));
            }
        }
    }
}
