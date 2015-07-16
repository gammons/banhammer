package me.grantammons.rogueEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import me.grantammons.rogueEngine.view.GameView;
import me.grantammons.rogueEngine.view.input.GameInputProcessor;

public class MainGame extends Game {
    private GameView gameView;
    private GameInputProcessor inputProcessor;

	@Override
	public void create () {
        InputMultiplexer im = new InputMultiplexer();
        inputProcessor = new GameInputProcessor();
        gameView = new GameView(inputProcessor);
        im.addProcessor(gameView.getUIInputProcessor());
        im.addProcessor(inputProcessor);

        Gdx.input.setInputProcessor(im);
        setScreen(gameView);
	}

    @Override
	public void render () {
        super.render();
	}
}
