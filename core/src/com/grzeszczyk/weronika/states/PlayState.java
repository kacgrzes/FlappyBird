package com.grzeszczyk.weronika.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grzeszczyk.weronika.sprites.Bird;
import com.grzeszczyk.weronika.sprites.Tube;

public class PlayState extends State {
    private final int STARTING_POSITION_X = 50;
    private final int STARTING_POSITION_Y = 300;
    private Bird bird;
    private Tube tube;

    private Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(STARTING_POSITION_X, STARTING_POSITION_Y);

        background = new Texture("bg.png");
        tube = new Tube(100);
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
            0
        );
        spriteBatch.draw(
            tube.getTopTubeTexture(),
            tube.getTopTubePosition().x,
            tube.getTopTubePosition().y
        );
        spriteBatch.draw(
            tube.getBottomTubeTexture(),
            tube.getBottomTubePosition().x,
            tube.getBottomTubePosition().y
        );
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
