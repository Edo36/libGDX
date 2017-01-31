package com.rpg.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Andrei on 29.07.2016.
 */
public class RenderComponent implements Component {

    private TextureRegion region;
    private Color debugColor = Color.WHITE;

    public static RenderComponent create() {
        return new RenderComponent();
    }

    public RenderComponent() {
        region = new TextureRegion();
    }

    public void setRenderComponent(TextureRegion region) {
        this.region = region;
    }

    public TextureRegion getRenderComponent() {
        return region;
    }

    public Color getDebugColor() {
        return debugColor;
    }

    public RenderComponent setDebugColor(Color color) {
        this.debugColor = color;
        return this;
    }
}
