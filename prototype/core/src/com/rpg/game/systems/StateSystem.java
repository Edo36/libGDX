package com.rpg.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.rpg.game.components.AIComponent;
import com.rpg.game.components.AnimationComponent;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.MovementComponent;
import com.rpg.game.components.RenderComponent;
import com.rpg.game.components.StateComponent;

/**
 * Created by Andrei on 29.07.2016.
 */
public class StateSystem extends IteratingSystem {

    //private ComponentMapper<StateComponent> stateMapper;

    public StateSystem() {
        super(Family.all(StateComponent.class).get());

        //stateMapper = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        cleanUp(entity);

//        if (Mappers.state.get(entity).getState() == -1) {
//            entity.removeAll();
//            this.getEngine().removeEntity(entity);
//        } else
            Mappers.state.get(entity).updateTime(deltaTime);
    }

    public void cleanUp(Entity entity) {

        //Gdx.app.log("Health", String.valueOf(Mappers.combat.get(entity).getHealth()));

        // remove dead entities in two passes
        // check if it breaks anything when it's done in one pass

        if (Mappers.combat.get(entity).getHealth() < 0) {
            //Mappers.state.get(entity).setState(-1);
            entity.remove(AIComponent.class);
            entity.remove(MovementComponent.class);
            entity.remove(AnimationComponent.class);
        }
    }
}
