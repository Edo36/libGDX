package com.rpg.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.PlayScreen;
import com.rpg.game.components.AIComponent;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.TargetComponent;

import java.util.Arrays;

/**
 * Created by Andrei on 11.08.2016.
 * http://www.roguebasin.com/index.php?title=Roguelike_Intelligence_-_Stateless_AIs
 */

public class AISystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(AIComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // basic stateless AI
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);
            // if it doesn't have target, scan the aggro-range for targets
            if (!Mappers.ai.get(entity).hasTarget())
                checkForTargets(entity, entities);

                // else attack target, if possible; if not, move towards target
            else {
                Entity target = Mappers.target.get(entity).getTarget();

                if (targetIsInRange(entity, target)) {
                    Gdx.app.log("Target in range!", "");
                    attackTarget(entity, target, deltaTime);
                } else {
                    Gdx.app.log("Moving to target!", "");
                    moveToTarget(entity, target);
                }
            }

            // manage the attack timer
            if (Mappers.combat.get(entity).getAttackTimer() > deltaTime)
                Mappers.combat.get(entity).updateAttackTimer(-deltaTime);
        }
    }

    private void checkForTargets(Entity e, ImmutableArray<Entity> targets) {
        // todo: go for the nearest target

        Gdx.app.log("Check for targets!", "");

        for (Entity t : targets) {

            // check entities faction
            if (Mappers.ai.get(e).getFlag() == Mappers.ai.get(t).getFlag())
                continue;



                // check for targets within aggro range
            if (Vector2.dst(
                    Mappers.transform.get(t).getPosition().x,
                    Mappers.transform.get(t).getPosition().y,
                    Mappers.transform.get(e).getPosition().x,
                    Mappers.transform.get(e).getPosition().y)
                    < Mappers.ai.get(e).getAggroRange()) {

                e.add(new TargetComponent(t));
                Mappers.ai.get(e).setHasTarget(true);

                // stop searching when target is found
                break;
            }
        }
    }

    private boolean targetIsInRange(Entity e, Entity t) {

        // check if the distance between entity and target is less than their radius sum
        // in other words, if their circle bounds are tangent or overlapping

        // need a dot product to determine distance sign
        float dot = Vector2.dot(Mappers.transform.get(t).getPosition().x, Mappers.transform.get(t).getPosition().y,
                Mappers.transform.get(e).getPosition().x, Mappers.transform.get(e).getPosition().y);
        int sign = dot < 0 ? -1 : 1;

        int radSum = Mappers.transform.get(e).getRadius() + Mappers.transform.get(t).getRadius();

        float distance = sign * Vector2.dst(
                Mappers.transform.get(t).getPosition().x, Mappers.transform.get(t).getPosition().y,
                Mappers.transform.get(e).getPosition().x, Mappers.transform.get(e).getPosition().y);

        return (distance - radSum < 0.1f);
    }

    private void attackTarget(Entity e, Entity t, float deltaTime) {
        //todo: aggro on first damage; threat levels

        // attack speed dictates how many attacks per second the attacker can do
        // attack timer tracks the time since last attack, to check if its time for another attack
        float attackSpeed = Mappers.combat.get(e).getAttackSpeed();
        float attackTimer = Mappers.combat.get(e).getAttackTimer();
        int damage = -Mappers.combat.get(e).getDamage();

        if (attackTimer <= deltaTime) {
            // if it's time for a new attack, reset the timer
            Mappers.combat.get(e).setAttackTimer(attackSpeed);
            // remove health from target
            Mappers.combat.get(t).modHealth(damage);
            Gdx.app.log("Attacked!", "");

            // if target is dead, remove the target component
            if (Mappers.combat.get(t).getHealth() < 0) {
                Gdx.app.log("Target dead!", "");
                e.remove(TargetComponent.class);
                Mappers.ai.get(e).setHasTarget(false);
            }
        }
    }

    private void moveToTarget(Entity e, Entity t) {

        float entityRadius = Mappers.transform.get(e).getRadius();
        float targetRadius = Mappers.transform.get(t).getRadius();
        float finalDistance = entityRadius + targetRadius;

        float dx = Mappers.transform.get(e).getPosition().x - Mappers.transform.get(t).getPosition().x;
        float dy = Mappers.transform.get(e).getPosition().y - Mappers.transform.get(t).getPosition().y;

        // float distance = Vector2.dst(x, y, tx, ty);
        float currentDistance = (float) Math.sqrt(dx * dx + dy * dy);
        float dest_dX = finalDistance * (dx / currentDistance);
        float dest_dY = finalDistance * (dy / currentDistance);

        Mappers.movement.get(e).setDestination(
                Mappers.transform.get(t).getPosition().x + dest_dX,
                Mappers.transform.get(t).getPosition().y + dest_dY);
    }
}
