package com.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Andrei on 30.07.2016.
 */
public class Assets {
    // use an atlas later, to store all state textures
    // public static TextureAtlas playerAtlas;
    // for now, use a single texture
    public static Texture playerIdle;
    public static Animation animationTest;
    public static TextureRegion regionTest;

    public static String test;


    public static Texture playerWalk;

    public static Animation[][] playerAnimations;

    public static Animation[] animations;


//    public static Texture loadTexture(String file) {
//        return new Texture(Gdx.files.internal(file));
//    }

    public static Animation[][] zergAnimation;
    public static Texture zergWalk;
    public static Texture zergAttack;

    public static void load() {
        // playerAtlas = new TextureAtlas(Gdx.files.internal("playerAtlas.pack"));
        playerWalk = new Texture(Gdx.files.internal("barbarianWalk.png"));
        playerIdle = new Texture(Gdx.files.internal("barbarianIdle.png"));

//        int frameWidth = playerWalk.getWidth() / 8;
//        int frameHeight = playerWalk.getHeight() / 16;

        int frameWidth = 76;
        int frameHeight = 82;

        // store player animations in an array[state][direction]
        // state is the action - attack, walk, etc.
        // direction is the orientation of the sprite - up, left, upper-left, etc.
        // in this case, we got 1 state, and 16 directions
        playerAnimations = new Animation[2][16];

        // for now, we have only one state, more to come
        //for (int state = 0; state < 2; state++) {
        for (int direction = 0; direction < 16; direction++) {
            // the texture sheet has 8 frames for each direction
            // store the current direction frames in a new array
            TextureRegion[] frames = new TextureRegion[8];
            for (int frame = 0; frame < 8; frame++)
                frames[frame] = new TextureRegion(
                        playerWalk,
                        frame * frameWidth,
                        direction * frameHeight,
                        frameWidth, frameHeight
                );

            //Animation animation = new Animation(1f / 8, frames);
            // the texture directions are clockwise, while the direction index is counter-clockwise
            // so we populate the array starting with the last position
            playerAnimations[1][15 - direction] = new Animation(1f / 8, frames);
            playerAnimations[0][15 - direction] = new Animation(1f / 8, frames);
        }

        frameWidth = playerIdle.getWidth() / 10;
        frameHeight = playerIdle.getHeight() / 16;

        for (int direction = 0; direction < 16; direction++) {
            // the texture sheet has 8 frames for each direction
            // store the current direction frames in a new array
            TextureRegion[] frames = new TextureRegion[10];
            for (int frame = 0; frame < 10; frame++)
                frames[frame] = new TextureRegion(
                        playerIdle,
                        frame * frameWidth,
                        direction * frameHeight,
                        frameWidth, frameHeight
                );

            //Animation animation = new Animation(1f / 8, frames);
            // the texture directions are clockwise, while the direction index is counter-clockwise
            // so we populate the array starting with the last position
            //playerAnimations[0][15 - direction] = new Animation(1f / 10, frames);
        }
        //}

        animations = new Animation[16];

        for (int index = 0; index < 16; index++) {
            //TextureRegion[] regions = new TextureRegion[8];
            Array<TextureRegion> regions = new Array<TextureRegion>();
            for (int frame = 0; frame < 8; frame++)
//                regions[frame] = new TextureRegion(playerWalk, frame*76, index*82, 76, 82);
                regions.add(new TextureRegion(playerWalk, frame * 76, index * 82, 76, 82));

            Animation animation = new Animation(1f / 8, regions);
            //animation.setPlayMode(Animation.PlayMode.LOOP);
            animations[15 - index] = animation;
        }


        int idleW = playerIdle.getWidth() / 10;
        int idleH = playerIdle.getHeight() / 16;

        Array<TextureRegion> idleFrames = new Array<TextureRegion>();


        for (int i = 0; i < 10; i++) {
            idleFrames.add(new TextureRegion(playerIdle, idleW * i, 0, idleW, idleH));
        }

        regionTest = new TextureRegion(playerIdle, 0, 0, idleW, 81);

        animationTest = new Animation(1f / 10, idleFrames);



        // zerg stuff

        zergAnimation = new Animation[2][16];
        zergWalk = new Texture(Gdx.files.internal("zerg_walk1.png"));

        for(int direction = 0; direction < 16; direction++) {
            Array<TextureRegion> regions = new Array<TextureRegion>();
            for(int frame = 0; frame < 7; frame++)
                regions.add(new TextureRegion(zergWalk, direction*43, frame*42, 42, 43));
            Animation animation = new Animation(1f/7, regions);

            zergAnimation[0][15-direction] = animation;
            zergAnimation[1][15-direction] = animation;
        }


    }
}
