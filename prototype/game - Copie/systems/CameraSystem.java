package com.rpg.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.rpg.game.World;
import com.rpg.game.components.CameraComponent;
import com.rpg.game.components.Mappers;
import com.rpg.game.components.TransformComponent;

/**
 * Created by Andrei on 31.07.2016.
 */

public class CameraSystem extends IteratingSystem {

    public CameraSystem() {
        // need to find a way to retrieve only the player
        // there's going to be just one entity (the player) with attached camera
        super(Family.all(CameraComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        CameraComponent camera = Mappers.camera.get(entity);
        float positionX = Mappers.transform.get(entity).getPosition().x;
        float positionY = Mappers.transform.get(entity).getPosition().y;

        // keep entity in the middle of the screen
        // if camera is about to cross edges, stop camera movement
        // camera position is its center point, so (camera_width/2, camera_height/2)
        // world size is (map tiles)*(tile size) in pixels
        if (positionX < camera.getWidth() / 2f)
            positionX = camera.getWidth() / 2f;
        else if (positionX > World.WORLD_WIDTH - camera.getWidth() / 2f)
            positionX = World.WORLD_WIDTH - camera.getWidth();

        if (positionY < camera.getHeight() / 2f)
            positionY = camera.getHeight() / 2f;
        else if (positionY > World.WORLD_HEIGHT - camera.getHeight() / 2f)
            positionY = World.WORLD_HEIGHT - camera.getHeight();

        camera.setPosition(positionX, positionY);
    }
}
