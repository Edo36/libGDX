package com.rpg.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.rpg.game.PlayScreen;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.MovementComponent;
import com.rpg.game.components.StateComponent;
import com.rpg.game.components.TransformComponent;

/**
 * Created by Andrei on 31.07.2016.
 */
public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(MovementComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mappers.transform.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);
        StateComponent state = Mappers.state.get(entity);

        // FYI
        // Vector2 adjustment = vel.speed.cpy().scl(deltaTime);
        // pos.position.add(adjustment.x, adjustment.y, 0f);

        // could use Vector2.dst(x1, y1, x2, y2) to get this distance
        // I think it's faster this way, without square root ?

        if(PlayScreen.collisionPoly.contains(transform.getPosition().x+movement.getVelocity().x,
                transform.getPosition().y+movement.getVelocity().y)) {
            //movement.setDestination(transform.getPosition().x, transform.getPosition().y);
            movement.setVelocity(0f, 0f);
        }

        if (!(Math.abs(transform.getPosition().x - movement.getDestination().x) <= movement.getVelocity().x) &&
                !(Math.abs(transform.getPosition().y - movement.getDestination().y) <= movement.getVelocity().y) ) {

            // some collision debug
            if (PlayScreen.collisionPoly.contains(transform.getPosition().x, transform.getPosition().y))
                Gdx.app.log("collided", "");

                //

            // some trigonometry to determine movement on x and y
            // could use some Vector2 or MathUtils methods
            float dx = movement.getDestination().x - transform.getPosition().x;
            float dy = movement.getDestination().y - transform.getPosition().y;

            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            float distanceX = dx / distance;
            float distanceY = dy / distance;

            // the speed variable is used inside the method
            movement.setVelocity(deltaTime * distanceX, deltaTime * distanceY);

            // set direction index for the animation array
            // get the angle between current position and destination
            // convert to 1-360 degrees; normally it is positive or negative 1-180 degrees
            // direction will be used to pick the proper animation orientation
            // 16 is the number of possible sprite rotations for the current assets
            // for info: direction radians * transform rotation = sprite spinning
            float directionAngle = MathUtils.atan2(dy, dx) * MathUtils.radiansToDegrees;
            directionAngle = (directionAngle > 0.01f) ? directionAngle : (360.0f + directionAngle);
            transform.setDirection((int) directionAngle * 16 / 360);

            // update entity position and bounds
            transform.addToPosition(movement.getVelocity().x, movement.getVelocity().y);
            Mappers.bounds.get(entity).setBounds(transform.getPosition().x, transform.getPosition().y);

            // move the state change in the action system
            if (state.getState() != 1) {
                Gdx.app.log("status moving", "");
                state.setState(1); // MOVING state
            }
        } else if (state.getState() != 0) {
            state.setState(0); // IDLE state; can be used as "target found" also
            Gdx.app.log("status idle", "");
        }
    }
}
