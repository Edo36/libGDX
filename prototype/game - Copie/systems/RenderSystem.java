package com.rpg.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.Assets;
import com.rpg.game.components.AnimationComponent;
import com.rpg.game.components.RenderComponent;
import com.rpg.game.components.TransformComponent;

import java.util.Comparator;

/**
 * Created by Andrei on 29.07.2016.
 */
public class RenderSystem extends IteratingSystem {
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;

    private ComponentMapper<RenderComponent> renderMapper;
    private ComponentMapper<TransformComponent> transformMapper;


    public RenderSystem(SpriteBatch batch, OrthographicCamera camera) {
        super(Family.all(RenderComponent.class).get());

        renderQueue = new Array<Entity>();

        // need ZCompare method ? when is this comparator used ?
//        comparator = new Comparator<Entity>() {
//            @Override
//            public int compare(Entity entityA, Entity entityB) {
//                return (int) Math.signum(transformMapper.get(entityB).getPosition().z -
//                        transformMapper.get(entityA).getPosition().z);
//            }
//        };

        renderMapper = ComponentMapper.getFor(RenderComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //renderQueue.sort();

        batch.setProjectionMatrix(camera.combined); // important
        batch.begin();

        for (Entity entity: renderQueue) {
            RenderComponent renderComponent = renderMapper.get(entity);
            // get the whole transform component, in case we need more than just position
            TransformComponent transformComponent = transformMapper.get(entity);

            // float width = renderComponent.getRenderComponent().getRegionWidth();
            // float height = renderComponent.getRenderComponent().getRegionHeight();

            batch.draw(
                    renderComponent.getRenderComponent(),
                    transformComponent.getPosition().x-transformComponent.getWidth()/2,
                    transformComponent.getPosition().y-transformComponent.getHeight()/2,
                    transformComponent.getWidth(), transformComponent.getHeight()
            );

// older code:
// if (renderComponent.region == null)
// continue; // why ?

//            float originX = width * 0.5f;
//            float originY = width * 0.5f;

//            batch.draw(
//                    renderComponent.region,
//                    transformComponent.position.x - originX, transformComponent.position.y - originY,
//                    originX, originY,
//                    width, height,
//                    transformComponent.scale, transformComponent.scale,
//                    MathUtils.radiansToDegrees * transformComponent.rotation
//            );
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    // used for the camera component
    public OrthographicCamera getCamera() {
        return camera;
    }
}