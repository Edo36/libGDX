package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Andrei on 29.07.2016.
 */
public class AnimationComponent implements Component{
    // each state has animations for 16 directions
    // so the array looks like this [states][directions]
    private Animation[][] animations;

    public AnimationComponent(int states, int directions) {
        animations = new Animation[states][directions];
    }

    public Animation getAnimation(int state, int direction) {
        return animations[state][direction];
    }

    public void setAnimations(Animation[][] animations) {
        this.animations = animations;
    }
}
