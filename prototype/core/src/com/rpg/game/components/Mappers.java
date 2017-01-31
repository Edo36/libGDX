package com.rpg.game.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 * Created by Andrei on 10.08.2016.
 */
// utility class for component mappers
public class Mappers {
    // public static final ComponentMapper<component> c = ComponentMapper.getFor(component.class);

    public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<BoundsComponent> bounds = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<CameraComponent> camera = ComponentMapper.getFor(CameraComponent.class);
    public static final ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<RenderComponent> render = ComponentMapper.getFor(RenderComponent.class);
    public static final ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<TargetComponent> target = ComponentMapper.getFor(TargetComponent.class);
    public static final ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<CombatComponent> combat = ComponentMapper.getFor(CombatComponent.class);
    public static final ComponentMapper<AIComponent> ai = ComponentMapper.getFor(AIComponent.class);
    public static final ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
}
