package com.grzeszczyk.weronika;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grzeszczyk.weronika.states.GameStateManager;
import com.grzeszczyk.weronika.states.MenuState;

public class FlappyBird extends ApplicationAdapter {

	public static int WIDTH = 240;
	public static int HEIGHT = 400;
	public static int SCALE = 1;

	private GameStateManager gameStateManager;
	private SpriteBatch spriteBatch;

	private Music music;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
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
		super.dispose();
		spriteBatch.dispose();
		music.dispose();
	}
}
