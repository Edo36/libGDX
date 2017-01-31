package com.rpg.game.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Andrei on 29.07.2016.
 */
public class StateComponent implements Component {
    private int state = 0; // default state idle
    private float time = 0.0f;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        time = 0.0f;
    }

    public void updateTime(float deltaTime) {
        time += deltaTime;
    }

    public float getTime() {
        return time;
    }

    public int type = 99; //just a player check
}
