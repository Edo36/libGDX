package com.rpg.game.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Andrei on 10.08.2016.
 */
public class BehaviourComponent implements Component {
    // use a flag to separate enemy, friend, neutral

    // 0 = player
    // 1 = "green" - friendly to player and hostile to "red"
    // 2 = "red" - hostile to player and "green"
    // 3 = neutral
    private int flag = 2;

    private int aggroRange = 30;

    private boolean hasTarget = false;

    public BehaviourComponent(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public void setHasTarget(Boolean b) {
        hasTarget = b;
    }

    public int getAggroRange() {
        return aggroRange;
    }

    public void setAggroRange(int range) {
        aggroRange = range;
    }
}
