package com.rpg.game.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Andrei on 11.08.2016.
 */
public class AIComponent implements Component {
    private boolean hasTarget = false;
    private int flag = 0;
    private int aggroRange = 100;

    public AIComponent(int flag) {
        this.flag = flag;
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getAggroRange() {
        return aggroRange;
    }

    public void setAggroRange(int aggroRange) {
        this.aggroRange = aggroRange;
    }
}
