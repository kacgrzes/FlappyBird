package com.grzeszczyk.weronika.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.grzeszczyk.weronika.sprites.Bird;
import com.grzeszczyk.weronika.sprites.Tube;

public class PlayState extends State {
    private final static int TUBE_SPACING = 125;
    private final static int TUBE_COUNT = 4;
    private final static int GROUND_Y_OFFSET = -56;

    private final int STARTING_POSITION_X = 50;
    private final int STARTING_POSITION_Y = 300;
    private Bird bird;

    private Array<Tube> tubes;

    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(STARTING_POSITION_X, STARTING_POSITION_Y);

        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x - (camera.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPosition2 = new Vector2(camera.position.x - (camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * TUBE_SPACING + Tube.TUBE_WIDTH));
        }
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
        camera.position.x = bird.getPosition().x + 80;

        updateTubes();
        updateGround();

        camera.update();
    }

    private void updateTubes() {
        for(int i = 0; i < tubes.size; i ++) {
            Tube tube = tubes.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) > tube.getTopTubePosition().x + Tube.TUBE_WIDTH) {
                tube.reposition(tube.getTopTubePosition().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if (tube.isColliding(bird.getBounds())) {
                resetGame();
            }
        }
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition1.x + ground.getWidth()) {
            groundPosition1.add(ground.getWidth() * 2, 0);
        }

        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + ground.getWidth()) {
            groundPosition2.add(ground.getWidth() * 2, 0);
        }

        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            resetGame();
        }
    }

    private void resetGame() {
        gameStateManager.set(new PlayState(gameStateManager));
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
        for (Tube tube : tubes) {
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
        }
        spriteBatch.draw(
                ground,
                groundPosition1.x,
                groundPosition1.y
        );
        spriteBatch.draw(
                ground,
                groundPosition2.x,
                groundPosition2.y
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
        background.dispose();
        bird.dispose();
        for(Tube tube : tubes) {
            tube.dispose();
        }
        ground.dispose();
    }
}
