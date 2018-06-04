package com.grzeszczyk.weronika.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture bottomTubeTexture, topTubeTexture;
    private Random random;
    private Vector2 bottomTubePosition, topTubePosition;
    private Rectangle bottomBounds, topBounds;

    public Tube(float x) {
        bottomTubeTexture = new Texture("bottomtube.png");
        topTubeTexture = new Texture("toptube.png");
        random = new Random();

        topTubePosition = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomTubePosition = new Vector2(x, topTubePosition.y - TUBE_GAP - bottomTubeTexture.getHeight());

        topBounds = new Rectangle(topTubePosition.x, topTubePosition.y, topTubeTexture.getWidth(), topTubeTexture.getHeight());
        bottomBounds = new Rectangle(bottomTubePosition.x, bottomTubePosition.y, bottomTubeTexture.getWidth(), bottomTubeTexture.getHeight());
    }

    public Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public Vector2 getBottomTubePosition() {
        return bottomTubePosition;
    }

    public Vector2 getTopTubePosition() {
        return topTubePosition;
    }

    public void reposition(float x) {
        topTubePosition.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomTubePosition.set(x, topTubePosition.y - TUBE_GAP - bottomTubeTexture.getHeight());

        bottomBounds.setPosition(bottomTubePosition.x, bottomTubePosition.y);
        topBounds.setPosition(topTubePosition.x, topTubePosition.y);
    }

    public boolean isColliding(Rectangle player) {
        return player.overlaps(bottomBounds) || player.overlaps(topBounds);
    }

    public void dispose() {
        bottomTubeTexture.dispose();
        topTubeTexture.dispose();
    }
}
