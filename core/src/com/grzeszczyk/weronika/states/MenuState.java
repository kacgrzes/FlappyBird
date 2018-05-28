package com.grzeszczyk.weronika.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grzeszczyk.weronika.FlappyBird;

/**
 * Created on 28.05.2018.
 */

public class MenuState extends State{
    private Texture background;
    private Texture playButton;
    int buttonWidth;
    int buttonHeight;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");

        buttonWidth = playButton.getWidth() * FlappyBird.SCALE;
        buttonHeight = playButton.getHeight() * FlappyBird.SCALE;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        spriteBatch.draw(playButton, (FlappyBird.WIDTH / 2) - (buttonWidth / 2), (FlappyBird.HEIGHT / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
