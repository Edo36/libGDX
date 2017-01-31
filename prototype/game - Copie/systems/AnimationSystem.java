package com.rpg.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.rpg.game.components.AnimationComponent;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.RenderComponent;
import com.rpg.game.components.StateComponent;

/**
 * Created by Andrei on 29.07.2016.
 */
public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Family.all(RenderComponent.class,
                AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponent render = Mappers.render.get(entity);
        StateComponent state = Mappers.state.get(entity);

        Animation animation = Mappers.animation.get(entity)
                .getAnimation(state.getState(), Mappers.transform.get(entity).getDirection());

        render.setRenderComponent(animation.getKeyFrame(state.getTime(), true));
        Mappers.transform.get(entity).setSize(render.getRenderComponent().getRegionWidth(),
                render.getRenderComponent().getRegionHeight());
    }
}
