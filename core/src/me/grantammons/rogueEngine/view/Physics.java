package me.grantammons.rogueEngine.view;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import me.grantammons.rogueEngine.core.Location;

import static me.grantammons.rogueEngine.core.Constants.PIXEL_HEIGHT;
import static me.grantammons.rogueEngine.core.Constants.PIXEL_WIDTH;

public class Physics {
    private World world;
    private RayHandler rayHandler;

    private Box2DDebugRenderer debugRenderer;

    public void init() {
        world = new World(new Vector2(0, 0), true);

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0f, 0f, 0f, 0.0f);
        rayHandler.setBlurNum(3);
        debugRenderer = new Box2DDebugRenderer();
    }

    public void update(OrthographicCamera cam) {

        world.step(1 / 60f, 6, 2);

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

    public Body addBody(Location location) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((location.x * PIXEL_WIDTH) + (PIXEL_WIDTH / 2), location.y * PIXEL_HEIGHT + (PIXEL_HEIGHT / 2));
        Body body = world.createBody(bodyDef);
        body.setUserData("Wall");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PIXEL_WIDTH / 2, PIXEL_HEIGHT / 2);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        body.createFixture(fd);
        shape.dispose();
        return body;
    }

}
