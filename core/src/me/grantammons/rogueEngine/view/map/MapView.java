package me.grantammons.rogueEngine.view.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import me.grantammons.rogueEngine.core.Location;
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
    private TextureRegion[][] dungeonRegions;

    public MapView(Map map) {
        this.map = map;
        Texture dungeon = new Texture("dungeon.png");
        dungeonRegions = TextureRegion.split(dungeon, 16, 16);
        setupDirt();
        blockSprite = new Sprite(new Texture("block.png"));
        blockSprite.setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
    }

    public void draw(Batch batch) {
        int[][] m = map.getMap();
        for(int y = 0; y < m.length; y++) {
            int[] mapRow = m[y];
            for (int x = 0; x < mapRow.length; x++) {
                if (m[y][x] == Map.BEDROCK)
                    paintEdgeTile(batch, y,x);
                if (m[y][x] == Map.GROUND) {
                    batch.draw(dungeonRegions[3][0], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
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

    private void paintEdgeTile(Batch batch, int y, int x) {
        if (map.tileAt(new Location(x,y + 1)) == Map.GROUND) paintWallBelow(batch, y, x);
        if (map.tileAt(new Location(x,y - 1)) == Map.GROUND) paintWallAbove(batch, y, x);
        if (map.tileAt(new Location(x + 1,y)) == Map.GROUND) paintWallRight(batch, y, x);
        if (map.tileAt(new Location(x - 1,y)) == Map.GROUND) paintWallLeft(batch, y, x);

        paintCorner(batch,y,x);

    }

    private void paintCorner(Batch batch, int y, int x) {
        if ((map.tileAt(new Location(x - 1, y)) == Map.BEDROCK)
                && (map.tileAt(new Location(x, y - 1)) == Map.BEDROCK)
                && (map.tileAt(new Location(x - 1, y - 1)) == Map.GROUND))
            paintWallAboveLeft(batch, y, x);

        if ((map.tileAt(new Location(x + 1, y)) == Map.BEDROCK)
                && (map.tileAt(new Location(x, y - 1 )) == Map.BEDROCK)
                && (map.tileAt(new Location(x + 1, y - 1)) == Map.GROUND))
            paintWallAboveRight(batch, y, x);

        if ((map.tileAt(new Location(x, y + 1)) == Map.BEDROCK)
                && (map.tileAt(new Location(x + 1, y )) == Map.BEDROCK)
                && (map.tileAt(new Location(x + 1, y + 1)) == Map.GROUND))
            paintWallBelowRight(batch, y, x);

        if ((map.tileAt(new Location(x, y + 1)) == Map.BEDROCK)
                && (map.tileAt(new Location(x - 1, y )) == Map.BEDROCK)
                && (map.tileAt(new Location(x - 1, y + 1)) == Map.GROUND))
            paintWallBelowLeft(batch, y, x);
    }

    private void paintWallBelow(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[2][1], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    private void paintWallAbove(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[0][1], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    private void paintWallRight(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[1][0], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    private void paintWallLeft(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[1][2], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }
    private void paintWallAboveLeft(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[0][2], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    private void paintWallAboveRight(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[0][0], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    private void paintWallBelowRight(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[2][0], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    private void paintWallBelowLeft(Batch batch, int y, int x) {
        batch.draw(dungeonRegions[2][2], x * PIXEL_WIDTH, y * PIXEL_HEIGHT);
    }

    public void setupDirt() {
        Texture dirtTexture = new Texture("dirtAnim.png");
        dirtRegions[0] = new TextureRegion(dirtTexture, 0,0,PIXEL_WIDTH,PIXEL_HEIGHT);
        dirtRegions[1] = new TextureRegion(dirtTexture, 15,0,PIXEL_WIDTH,PIXEL_HEIGHT);
        dirtRegions[2] = new TextureRegion(dirtTexture, 31,0,PIXEL_WIDTH,PIXEL_HEIGHT);
        dirtRegions[3] = new TextureRegion(dirtTexture, 47,0,PIXEL_WIDTH,PIXEL_HEIGHT);
    }
}
