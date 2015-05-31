package me.grantammons.banhammer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.grantammons.banhammer.view.GameView;

public class Banhammer extends Game {
	SpriteBatch batch;
	Texture img;

    private GameView gameView;

	@Override
	public void create () {
        gameView = new GameView();
        setScreen(gameView);
	}

	@Override
	public void render () {
        super.render();
	}
}
