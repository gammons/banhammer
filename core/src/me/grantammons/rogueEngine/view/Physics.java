package me.grantammons.rogueEngine.view;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Location;

import java.util.ArrayList;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class Physics {
    private World world;
    private RayHandler rayHandler;

    private Box2DDebugRenderer debugRenderer;

    public void init() {
        world = new World(new Vector2(0, 0), true);

        //RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0f, 0f, 0f, 0.0f);
        rayHandler.setBlurNum(3);

        debugRenderer = new Box2DDebugRenderer();
    }

    public void update(OrthographicCamera cam) {

        world.step(1 / 60f, 6, 2);
        rayHandler.diffuseBlendFunc.set(GL20.GL_DST_COLOR, GL20.GL_SRC_COLOR);

        //debugRenderer.render(world, cam.combined);
        rayHandler.setCombinedMatrix(cam.combined);
        rayHandler.updateAndRender();

    }

    public World getWorld() {
        return world;
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

    public Body addBody(Location location, ArrayList<Integer> surrounding) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;


        int heightDivider = 1;
        int widthDivider = 1;


        if ((surrounding.contains(Constants.NORTHEAST))
                || (surrounding.contains(Constants.NORTHWEST))
                || (surrounding.contains(Constants.SOUTHEAST))
                || (surrounding.contains(Constants.SOUTHWEST))) {
            widthDivider += 1;
            heightDivider += 1;
        }

        if (surrounding.contains(Constants.EAST)) {
            widthDivider += 1;
        }
        if (surrounding.contains(Constants.WEST)) {
            widthDivider += 1;
        }
        if (surrounding.contains(Constants.NORTH)) {
            heightDivider += 1;
        }
        if (surrounding.contains(Constants.SOUTH)) {
            heightDivider += 1;
        }

        int positionX = location.x * PIXEL_WIDTH + (PIXEL_WIDTH / 2);
        int positionY = location.y * PIXEL_HEIGHT + (PIXEL_HEIGHT / 2);

        bodyDef.position.set(positionX, positionY);
        Body body = world.createBody(bodyDef);
        body.setUserData("Wall");

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(PIXEL_WIDTH / widthDivider, PIXEL_HEIGHT / heightDivider);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        body.createFixture(fd);
        shape.dispose();
        return body;
    }

}
