package com.rpg.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.StreamUtils;
import com.rpg.game.PlayScreen;
import com.rpg.game.components.AIComponent;
import com.rpg.game.components.BehaviourComponent;
import com.rpg.game.components.BoundsComponent;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.MovementComponent;
import com.rpg.game.components.PlayerComponent;
import com.rpg.game.components.StateComponent;
import com.rpg.game.components.TargetComponent;

/**
 * Created by Andrei on 07.08.2016.
 */
public class PlayerSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private Entity player;

    private ComponentMapper<BoundsComponent> boundsMapper = ComponentMapper.getFor(BoundsComponent.class);
    private ComponentMapper<MovementComponent> movementMapper = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<TargetComponent> targetMapper = ComponentMapper.getFor(TargetComponent.class);
    private ComponentMapper<StateComponent> stateMapper = ComponentMapper.getFor(StateComponent.class);

    public PlayerSystem() {
    }

    public void addedToEngine(Engine engine) {
        //player = engine.getEntities().get(0);
        entities = engine.getEntities();
    }

    public void update(float deltaTime) {

        Entity player = entities.get(0);
        MovementComponent playerMovement = movementMapper.get(player);
        StateComponent state = stateMapper.get(player);
        AIComponent ai = Mappers.ai.get(player);


        // check if touch no longer matches previous target
        if (Mappers.player.get(player).hasTarget()) {
            TargetComponent targetComponent = targetMapper.get(player);
            Entity target = targetComponent.getTarget();
            BoundsComponent targetBounds = boundsMapper.get(target);

            // clear target if the touch was outside the previous target's box
            if ( !targetBounds.getBounds().contains(PlayScreen.inputLocation.x, PlayScreen.inputLocation.y)) {
                player.remove(TargetComponent.class);
                Mappers.player.get(player).setHasTarget(false);
            }
        }

        // if no target was set, check the touch location for a possible target
        if ( ! Mappers.player.get(player).hasTarget() )
        {
            for (int i = 1; i < entities.size(); ++i) {

                Entity entity = entities.get(i);
                BoundsComponent targetBounds = boundsMapper.get(entity);

                if (targetBounds.getBounds().contains(PlayScreen.inputLocation.x, PlayScreen.inputLocation.y)) {
                    //Gdx.app.log("target acquired", "");
                    player.add(new TargetComponent(entity));
                    Mappers.player.get(player).setHasTarget(true);
                    break;
                }
            }

            playerMovement.setDestination(PlayScreen.inputLocation.x, PlayScreen.inputLocation.y);
        }
    }
}
