package com.rpg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class RPG extends Game {
	//public SpriteBatch batch;
    //public static Logs logs = new Logs();

    @Override
    public void create() {
		//batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }

    @Override
    public void dispose() {
    }
}
