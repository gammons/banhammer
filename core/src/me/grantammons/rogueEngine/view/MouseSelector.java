package me.grantammons.rogueEngine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.utils.pathfinding.AStar;
import me.grantammons.rogueEngine.view.input.InputListener;

import java.util.ArrayList;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class MouseSelector implements InputListener, Drawable {

    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private Location location;
    private Game game;
    private ArrayList<Location> path;

    public MouseSelector(OrthographicCamera cam, Game game) {
        this.cam = cam;
        this.game = game;
        shapeRenderer = new ShapeRenderer();
        location = new Location();
        path = new ArrayList<>();
    }

    @Override
    public void notify(int direction) {

    }

    @Override
    public void notifyMouseMoved(int screenX, int screenY) {
        Vector3 coords = new Vector3(screenX, screenY, 0);
        cam.unproject(coords);
        coords.x /= PIXEL_WIDTH;
        coords.y /= PIXEL_HEIGHT;

        location.x = (int)coords.x;
        location.y = (int)coords.y;

        AStar AStar = new AStar(location, game.map);
        path =  AStar.compute(game.player.location);
    }

    @Override
    public void draw(Batch batch) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Location l : path) {
            shapeRenderer.setColor(0, 0, 0.5f, 0.5f);
            shapeRenderer.rect(l.x * PIXEL_WIDTH, l.y * PIXEL_HEIGHT, PIXEL_WIDTH, PIXEL_HEIGHT);
        }
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

    }
}
