package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * Created by Andrei on 07.08.2016.
 */
public class TargetComponent implements Component {

    private Entity target;

    public TargetComponent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity t) {
        target = t;
    }
}

