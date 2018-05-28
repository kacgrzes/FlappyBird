package com.grzeszczyk.weronika.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grzeszczyk.weronika.FlappyBird;

public class PlayState extends State {
    Texture bird;
    int birdWidth;
    int birdHeight;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        bird = new Texture("bird.png");
        birdWidth = bird.getWidth() * FlappyBird.SCALE;
        birdHeight = bird.getHeight() * FlappyBird.SCALE;

        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(bird, 50, 50, birdWidth, birdHeight);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
