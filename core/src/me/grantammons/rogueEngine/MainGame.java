package me.grantammons.rogueEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.grantammons.rogueEngine.view.input.GameInputProcessor;
import me.grantammons.rogueEngine.view.GameView;

public class MainGame extends Game {
	SpriteBatch batch;
	Texture img;

    private GameView gameView;
    private GameInputProcessor inputProcessor;

	@Override
	public void create () {

        inputProcessor = new GameInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        gameView = new GameView(inputProcessor);
        setScreen(gameView);
	}

	@Override
	public void render () {
        super.render();
	}
}
