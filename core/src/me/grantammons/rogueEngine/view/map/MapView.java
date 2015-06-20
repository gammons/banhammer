package me.grantammons.rogueEngine.view.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.grantammons.rogueEngine.core.Map;

/**
 * Created by grantammons on 5/30/15.
 */
public class MapView {
    int PIXEL_WIDTH = 16;
    int PIXEL_HEIGHT = 16;

    private Map map;
    private Sprite blockSprite;
    private Sprite dirtSprite;
    private TextureRegion[] dirtRegions = new TextureRegion[4];

    public MapView(Map map) {
        this.map = map;
        blockSprite = new Sprite(new Texture("block.png"));
        setupDirt();
        blockSprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    public void draw(Batch batch) {
        int[][] m = map.getMap();
        for(int y = 0; y < m.length; y++) {
            int[] mapRow = m[y];
            for (int x = 0; x < mapRow.length; x++) {
                if (m[y][x] == Map.BEDROCK) {
                    blockSprite.setPosition(x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
                    blockSprite.draw(batch);
                }
                if (m[y][x] == Map.DIRT) {
                    batch.draw(dirtRegions[0], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
                }
                if (m[y][x] == Map.DIRTDUG1) {
                    batch.draw(dirtRegions[1], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
                }
                if (m[y][x] == Map.DIRTDUG2) {
                    batch.draw(dirtRegions[2], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
                }
                if (m[y][x] == Map.DIRTDUG3) {
                    batch.draw(dirtRegions[3], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
                }
            }
        }
    }

    public void setupDirt() {
        Texture dirtTexture = new Texture("dirtAnim.png");
        dirtRegions[0] = new TextureRegion(dirtTexture, 0,0,PIXEL_WIDTH,PIXEL_HEIGHT);
        dirtRegions[1] = new TextureRegion(dirtTexture, 15,0,PIXEL_WIDTH,PIXEL_HEIGHT);
        dirtRegions[2] = new TextureRegion(dirtTexture, 31,0,PIXEL_WIDTH,PIXEL_HEIGHT);
        dirtRegions[3] = new TextureRegion(dirtTexture, 47,0,PIXEL_WIDTH,PIXEL_HEIGHT);
    }
}
