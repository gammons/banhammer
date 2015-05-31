package me.grantammons.banhammer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.grantammons.banhammer.view.GameInputProcessor;
import me.grantammons.banhammer.view.GameView;

public class Banhammer extends Game {
	SpriteBatch batch;
	Texture img;

    private GameView gameView;
    private GameInputProcessor inputProcessor;

	@Override
	public void create () {

        inputProcessor = new GameInputProcessor();
        gameView = new GameView(inputProcessor);
        setScreen(gameView);
	}

	@Override
	public void render () {
        super.render();
	}
}
