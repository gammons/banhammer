package me.grantammons.rogueEngine.view;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import me.grantammons.rogueEngine.core.Location;
import me.grantammons.rogueEngine.core.Map;
import me.grantammons.rogueEngine.core.entities.AnimatedEntity;

import java.util.ArrayList;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class FovRenderer implements Drawable {
    private static final int BUFFER = 5;


    private AnimatedEntity entity;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera cam;

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final Map map;

    public FovRenderer(OrthographicCamera cam, AnimatedEntity entity, Map map) {
        shapeRenderer = new ShapeRenderer();
        this.cam = cam;
        this.entity = entity;
        this.map = map;
    }

    @Override
    public void draw(Batch batch, TweenManager tweenManager) {
        shapeRenderer.setProjectionMatrix(cam.combined);

       Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1f);



        int xSize = (int)cam.viewportWidth / PIXEL_WIDTH;
        int ySize= (int)cam.viewportHeight / PIXEL_HEIGHT;
        int buffer = 5;

        setBounds(xSize, ySize);

        for(int x = startX; x < endX; x++) {
            for(int y = startY; y < endY; y++) {
                Location l = new Location(x,y);

                if (!entity.getVisibleTiles().contains(l)) {
                    renderFovCube(entity.getVisibleTiles(), l);
                }
            }
        }

        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

    private void setBounds(int xSize, int ySize) {
        startX = 0;
        if (entity.location.x - BUFFER > (xSize / 2))
            startX = entity.location.x - (xSize / 2) - BUFFER;
        startY = 0;
        if (entity.location.y - BUFFER > (ySize / 2))
            startY = entity.location.y - (ySize / 2) - BUFFER;

        endX = startX + xSize + BUFFER;
        if (endX > map.sizeX()) endX = map.sizeX();

        endY = startY + ySize + BUFFER;
        if (endX > map.sizeY()) endY = map.sizeY();
    }

   private void renderFovCube(ArrayList<Location> visibleTiles, Location l) {
        Color bottomLeftColor = new Color(0,0,0,1);
        Color bottomRightColor = new Color(0,0,0,1);
        Color topLeftColor = new Color(0,0,0,1);
        Color topRightColor = new Color(0,0,0,1);


        if (visibleTiles.contains(new Location(l.x - 1, l.y))) {
            bottomLeftColor.a = 0;
            topLeftColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x + 1, l.y))) {
            bottomRightColor.a = 0;
            topRightColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x, l.y + 1))) {
            topLeftColor.a = 0;
            topRightColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x, l.y - 1))) {
            bottomLeftColor.a = 0;
            bottomRightColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x - 1, l.y - 1))) {
            bottomLeftColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x + 1, l.y - 1))) {
            bottomRightColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x - 1, l.y + 1))) {
            topLeftColor.a = 0;
        } if (visibleTiles.contains(new Location(l.x + 1, l.y + 1))) {
            topRightColor.a = 0;
        }

        shapeRenderer.rect(l.x * PIXEL_WIDTH,
                l.y * PIXEL_HEIGHT,
                PIXEL_WIDTH,
                PIXEL_HEIGHT,
                bottomLeftColor,
                bottomRightColor,
                topRightColor,
                topLeftColor
        );
    }
}
