package com.rpg.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.PlayScreen;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.RenderComponent;
import com.rpg.game.components.TransformComponent;

/**
 * Created by Andrei on 10.08.2016.
 */
public class DebugRenderSystem extends IteratingSystem {
    private ShapeRenderer debugRenderer;
    private OrthographicCamera camera;

    private Array<Entity> renderQueue;

    public DebugRenderSystem(OrthographicCamera camera) {
        super(Family.all(RenderComponent.class).get());

        renderQueue = new Array<Entity>();

        this.camera = camera;
        debugRenderer = new ShapeRenderer();
        debugRenderer.setAutoShapeType(true);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin();
        debugRenderer.set(ShapeRenderer.ShapeType.Filled);

        for (Entity e: renderQueue) {
            RenderComponent rc = Mappers.render.get(e);
            TransformComponent tc = Mappers.transform.get(e);

            debugRenderer.setColor(rc.getDebugColor());
            debugRenderer.circle(tc.getPosition().x, tc.getPosition().y, tc.getHeight()/2);
            debugRenderer.setColor(Color.BLACK);
            debugRenderer.point(tc.getPosition().x, tc.getPosition().y, 0f);
        }

        // touch debug
        debugRenderer.setColor(Color.GRAY);
        debugRenderer.circle(PlayScreen.inputLocation.x, PlayScreen.inputLocation.y, 5);

        float[] vertices = PlayScreen.collisionPoly.getTransformedVertices();
        debugRenderer.polygon(vertices);

        debugRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
