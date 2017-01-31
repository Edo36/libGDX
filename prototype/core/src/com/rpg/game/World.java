package com.rpg.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.rpg.game.components.AIComponent;
import com.rpg.game.components.AnimationComponent;
import com.rpg.game.components.BoundsComponent;
import com.rpg.game.components.CameraComponent;
import com.rpg.game.components.CombatComponent;
import com.rpg.game.components.MovementComponent;
import com.rpg.game.components.BehaviourComponent;
import com.rpg.game.components.PlayerComponent;
import com.rpg.game.components.RenderComponent;
import com.rpg.game.components.StateComponent;
import com.rpg.game.components.TransformComponent;
import com.rpg.game.systems.RenderSystem;

/**
 * Created by Andrei on 30.07.2016.
 */
public class World {
    // todo: set world size programmatically, based on the tmx map
    public static int WORLD_WIDTH = 768;
    public static int WORLD_HEIGHT = 256;

    private Engine engine;

    public World(Engine engine) {
        this.engine = engine;
    }

    public void create() {
        Entity player = createPlayer();
        //Entity zerg = createZerg();
        Entity friendly = createFriendly();
        //Entity friendly2 = createFriendly2();
    }

    private Entity createPlayer() {
        Entity entity = new Entity();

        TransformComponent transformComponent = new TransformComponent(
                new Vector3(50f, 50f, 10f), // position, w, h, scale, rotation
                16, 17, 1f, 0f
        );

        MovementComponent movementComponent = new MovementComponent( // velocity, destination, speed
                new Vector2(0f, 0f),
                new Vector2(transformComponent.getPosition().x, transformComponent.getPosition().y),
                30
        );

        AnimationComponent animationComponent = new AnimationComponent(2, 16); // 1 state, 16 dir
        animationComponent.setAnimations(Assets.playerAnimations);

        StateComponent stateComponent = new StateComponent();
        stateComponent.setState(0);

        BoundsComponent boundsComponent = new BoundsComponent();
        boundsComponent.setBounds(
                transformComponent.getPosition().x,
                transformComponent.getPosition().y, 10
        );

        CameraComponent cameraComponent = new CameraComponent(
                engine.getSystem(RenderSystem.class).getCamera());

        entity.add(animationComponent);
        entity.add(boundsComponent);
        entity.add(cameraComponent);
        entity.add(movementComponent);
        entity.add(new RenderComponent());
        entity.add(stateComponent);
        entity.add(transformComponent);
        entity.add(new PlayerComponent());
//        entity.add(new AIComponent(0, true));
        entity.add(new CombatComponent(100, 1));

        entity.flags = 0;

        engine.addEntity(entity);

        return entity;
    }

    private Entity createZerg() {
        Entity entity = new Entity();

        TransformComponent transformComponent = new TransformComponent(
                new Vector3(100f, 100f, 10f), // position, w, h, scale, rotation
                16, 17, 1f, 0f
        );

        MovementComponent movementComponent = new MovementComponent(// velocity, destination, speed
                new Vector2(0f, 0f),
                new Vector2(100f, 100f),
                20
        );

        AnimationComponent animationComponent = new AnimationComponent(1, 16); // 1 state, 16 dir
        animationComponent.setAnimations(Assets.zergAnimation);

        StateComponent stateComponent = new StateComponent();
        stateComponent.setState(0);

        BoundsComponent boundsComponent = new BoundsComponent();
        boundsComponent.setBounds(
                transformComponent.getPosition().x,
                transformComponent.getPosition().y, 10
        );

        entity.add(animationComponent);
        entity.add(boundsComponent);
        entity.add(movementComponent);
        entity.add(RenderComponent.create().setDebugColor(Color.RED));
        entity.add(stateComponent);
        entity.add(transformComponent);
        //entity.add(new BehaviourComponent(1));
        entity.add(new CombatComponent(50, 5));
        entity.add(new AIComponent(1));

        engine.addEntity(entity);

        return entity;
    }

    private Entity createFriendly() {
        Entity entity = new Entity();

        TransformComponent transformComponent = new TransformComponent(
                new Vector3(50f, 50f, 10f), // position, w, h, scale, rotation
                16, 17, 1f, 0f
        );

        MovementComponent movementComponent = new MovementComponent( // velocity, destination, speed
                new Vector2(0f, 0f),
                new Vector2(50f, 50f),
                20
        );

        AnimationComponent animationComponent = new AnimationComponent(1, 16); // 1 state, 16 dir
        animationComponent.setAnimations(Assets.zergAnimation);

        StateComponent stateComponent = new StateComponent();
        stateComponent.setState(0);

        BoundsComponent boundsComponent = new BoundsComponent();
        boundsComponent.setBounds(
                transformComponent.getPosition().x,
                transformComponent.getPosition().y, 10
        );

        entity.add(animationComponent);
        entity.add(boundsComponent);
        entity.add(movementComponent);
        entity.add(RenderComponent.create().setDebugColor(Color.GREEN));
        entity.add(stateComponent);
        entity.add(transformComponent);
//        entity.add(new BehaviourComponent(1));
        entity.add(new CombatComponent(30, 1));
        entity.add(new AIComponent(0));

        engine.addEntity(entity);

        return entity;
    }

    private Entity createFriendly2() {
        Entity entity = new Entity();

        TransformComponent transformComponent = new TransformComponent(
                new Vector3(80f, 50f, 10f), // position, w, h, scale, rotation
                16, 17, 1f, 0f
        );

        MovementComponent movementComponent = new MovementComponent( // velocity, destination, speed
                new Vector2(0f, 0f),
                new Vector2(10f, 50f),
                5
        );

        AnimationComponent animationComponent = new AnimationComponent(1, 16); // 1 state, 16 dir
        animationComponent.setAnimations(Assets.zergAnimation);

        StateComponent stateComponent = new StateComponent();
        stateComponent.setState(0);

        BoundsComponent boundsComponent = new BoundsComponent();
        boundsComponent.setBounds(
                transformComponent.getPosition().x,
                transformComponent.getPosition().y, 10
        );

        entity.add(animationComponent);
        entity.add(boundsComponent);
        entity.add(movementComponent);
        entity.add(RenderComponent.create().setDebugColor(Color.GREEN));
        entity.add(stateComponent);
        entity.add(transformComponent);
//        entity.add(new BehaviourComponent(1));
        entity.add(new CombatComponent(30, 1));
        entity.add(new AIComponent(0));

        engine.addEntity(entity);

        return entity;
    }
}
