package me.grantammons.banhammer.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.banhammer.core.Player;

/**
 * Created by grantammons on 5/30/15.
 */
public class PlayerView implements InputListener {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;

    private Sprite sprite;
    private Player player;

    public PlayerView() {
        player = new Player();
        player.x = 0;
        player.y = 0;

        sprite = new Sprite(new Texture("player.png"));
        sprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    public void draw(Batch batch) {
        sprite.setPosition(player.x * PIXEL_WIDTH, player.y * PIXEL_HEIGHT);
        sprite.draw(batch);
    }

    @Override
    public void notify(String direction) {
        if (direction == "up") player.y++;
        if (direction == "down") player.y--;
        if (direction == "left") player.x--;
        if (direction == "right") player.x++;
    }
}
