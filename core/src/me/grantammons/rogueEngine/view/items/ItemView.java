package me.grantammons.rogueEngine.view.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.items.Item;

public class ItemView {
    protected Item item;
    protected Sprite sprite;
    protected Game game;

    public ItemView(Game game, Item item) {
        sprite = new Sprite(new Texture(item.getSpriteFile()));
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(Constants.PIXEL_WIDTH, Constants.PIXEL_HEIGHT);
        this.item = item;
        this.game = game;

    }

    public void draw(Batch batch) {
        if (item.isExpired()) return;
        batch.draw(sprite, item.location.x * Constants.PIXEL_WIDTH, item.location.y * Constants.PIXEL_HEIGHT);
    }

    public Item getItem() {
        return item;
    }
}
