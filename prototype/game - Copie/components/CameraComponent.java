package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Andrei on 31.07.2016.
 */
public class CameraComponent implements Component {

    private OrthographicCamera camera;

    public CameraComponent(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setPosition(float x, float y) {
        camera.position.set(x, y, 0f);
        camera.update();
    }

    public float getWidth() {
        return camera.viewportWidth;
    }

    public float getHeight() {
        return camera.viewportHeight;
    }
}
