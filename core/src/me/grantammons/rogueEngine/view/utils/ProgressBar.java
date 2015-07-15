package me.grantammons.rogueEngine.view.utils;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.view.Drawable;

public class ProgressBar implements Drawable {

    public static final int HEIGHT = 2;

    private int x;
    private int y;

    private int min;
    private int max;
    private int current;
    private ShapeRenderer shapeRenderer;

    public ProgressBar(int min, int max, int current, int x, int y) {
        this.min = min;
        this.max = max;
        this.x = x;
        this.y = y;
        this.current = current;
        shapeRenderer = new ShapeRenderer();
    }

    public void update(int current) {
        this.current = current;
    }

    public void setProjectionMatrix(Matrix4 matrix) {
        shapeRenderer.setProjectionMatrix(matrix);
    }


    @Override
    public void draw(Batch batch, TweenManager tweenManager) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        float percent = current / (float)max;
        shapeRenderer.rect(x, y, Constants.PIXEL_WIDTH * percent, HEIGHT);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.OLIVE);
        shapeRenderer.rect(x, y, Constants.PIXEL_WIDTH, HEIGHT);
        shapeRenderer.end();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
