package com.grzeszczyk.weronika.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.grzeszczyk.weronika.FlappyBird;

public class Bird {
    private Sound flap;

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;

    private Texture texture;
    private Animation animation;

    private int birdWidth;
    private int birdHeight;

    private Rectangle bounds;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        texture = new Texture("birdanimation.png");
        animation = new Animation(texture, 3, 0.6f);

        birdWidth = texture.getWidth() / 3 * FlappyBird.SCALE;
        birdHeight = texture.getHeight() * FlappyBird.SCALE;

        bounds = new Rectangle(x, y, birdWidth, birdHeight);

        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        animation.update(dt);

        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 0) {
            position.y = 0;
        }

        bounds.setPosition(position.x, position.y);

        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return animation.getFrame();
    }

    public int getBirdWidth() {
        return birdWidth;
    }

    public int getBirdHeight() {
        return birdHeight;
    }

    public void jump(){
        velocity.set(0, 250, 0);
        flap.play(0.2f);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
