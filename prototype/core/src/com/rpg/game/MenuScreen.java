package com.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Andrei on 07.08.2016.
 */
public class MenuScreen extends ScreenAdapter {
    RPG game;

    Texture menu;
    SpriteBatch batch;
    OrthographicCamera camera;

    public MenuScreen(RPG game) {
        this.game = game;

        camera = new OrthographicCamera(256, 144);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        menu = new Texture(Gdx.files.internal("menu.png"));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.justTouched())
            game.setScreen(new PlayScreen(game));

        batch.begin();
        batch.draw(menu, 0, 0, 256, 144);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
        menu.dispose();
    }
}
