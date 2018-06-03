package com.grzeszczyk.weronika.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture bottomTubeTexture, topTubeTexture;
    private Random random;
    private Vector2 bottomTubePosition, topTubePosition;

    public Tube(float x) {
        bottomTubeTexture = new Texture("bottomtube.png");
        topTubeTexture = new Texture("toptube.png");
        random = new Random();

        topTubePosition = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        bottomTubePosition = new Vector2(x, topTubePosition.y - TUBE_GAP - bottomTubeTexture.getHeight());
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
}
