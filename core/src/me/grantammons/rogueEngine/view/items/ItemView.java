package me.grantammons.rogueEngine.view.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import me.grantammons.rogueEngine.core.Constants;
import me.grantammons.rogueEngine.core.Game;
import me.grantammons.rogueEngine.core.entities.items.Item;
import me.grantammons.rogueEngine.view.entities.EntityView;

public class ItemView extends EntityView {
    public ItemView(Game game, Item item) {
        super(game, item);
        sprite = new Sprite(new Texture(item.getSpriteFile()));
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite.setSize(Constants.PIXEL_WIDTH, Constants.PIXEL_HEIGHT);
    }
}
