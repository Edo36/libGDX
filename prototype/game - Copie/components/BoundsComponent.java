package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;

/**
 * Created by Andrei on 31.07.2016.
 */
public class BoundsComponent implements Component {
    private Circle bounds = new Circle();

    public void setBounds(float x, float y, int radius) {
        bounds.set(x, y, radius);
    }

    public void setBounds(float x, float y) {
        bounds.set(x, y, bounds.radius);
    }
    public Circle getBounds() {
        return bounds;
    }
}
