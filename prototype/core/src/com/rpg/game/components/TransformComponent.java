package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Andrei on 29.07.2016.
 */
public class TransformComponent implements Component {

    private Vector3 position;
    private int width, height, radius;
    private float scale, rotation;
    private int direction;

    public TransformComponent(Vector3 position, int width, int height, float scale, float rotation) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.rotation = rotation;

        // facing down - default orientation of the sprite, counter-clockwise
        direction = 12;
    }

    // maybe use a Vector2, and separate float Z ?
    public void setZ(int z) {
        position.z = z; // used only for renderqueue sorting
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        // keep the initial Z for renderqueue sorting
        position.set(x, y, position.z);
    }

    public void addToPosition(float x, float y) {
        position.add(x, y, 0f);
    }

    public void setSize(int width, int height) {
        this.width = width/3;
        this.height = height/3;
    }

    // public int getSize(String dimension) {
    // return dimension.equals("width") ? width : height;
    // }

    // width will be used to generate the hitbox size
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public int getRadius() {
        return Math.max(width, height)/2;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
