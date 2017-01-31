package com.rpg.game.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Andrei on 15.08.2016.
 */
public class PlayerComponent implements Component {
    private boolean hasTarget = false;

    public boolean hasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }


}
