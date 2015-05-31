package me.grantammons.banhammer.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.banhammer.core.Map;

/**
 * Created by grantammons on 5/30/15.
 */
public class MapView {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;

    private Map map;
    private Sprite blockSprite;

    public MapView(Map map) {
        this.map = map;
        blockSprite = new Sprite(new Texture("block.png"));
        blockSprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    public void draw(Batch batch) {
        int[][] m = map.getMap();
        for(int y = 0; y < m.length; y++) {
            int[] mapRow = m[y];
            for (int x = 0; x < mapRow.length; x++) {
                if (m[y][x] == 1) {
                    blockSprite.setPosition(x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
                    blockSprite.draw(batch);
                }
            }
        }
    }
}
