package com.grzeszczyk.weronika;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grzeszczyk.weronika.states.GameStateManager;
import com.grzeszczyk.weronika.states.MenuState;

public class FlappyBird extends ApplicationAdapter {

	public static int WIDTH = 480;
	public static int HEIGHT = 800;
	public static int SCALE = 2;

	private GameStateManager gameStateManager;
	private SpriteBatch spriteBatch;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 1, 0, 1);
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(spriteBatch);
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
	}
}
