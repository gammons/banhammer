package me.grantammons.banhammer.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by grantammons on 5/30/15.
 */
public class PlayerView {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;

    private Sprite sprite;

    public PlayerView() {
        sprite = new Sprite(new Texture("player.png"));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }
}
