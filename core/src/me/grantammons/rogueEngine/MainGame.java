package me.grantammons.rogueEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import me.grantammons.rogueEngine.view.GameView;
import me.grantammons.rogueEngine.view.input.GameInputProcessor;

public class MainGame extends Game {
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
