package com.grzeszczyk.weronika.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grzeszczyk.weronika.FlappyBird;
import com.grzeszczyk.weronika.sprites.Bird;

public class PlayState extends State {
    private final int STARTING_POSITION_X = 50;
    private final int STARTING_POSITION_Y = 300;
    private Bird bird;

    private Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(STARTING_POSITION_X, STARTING_POSITION_Y);
        camera.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        background = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(
            background,
            camera.position.x - (camera.viewportWidth / 2),
            0,
            camera.viewportWidth * FlappyBird.SCALE,
            camera.viewportHeight * FlappyBird.SCALE);
        spriteBatch.draw(
            bird.getTexture(),
            bird.getPosition().x,
            bird.getPosition().y,
            bird.getBirdWidth(),
            bird.getBirdHeight()
        );
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
