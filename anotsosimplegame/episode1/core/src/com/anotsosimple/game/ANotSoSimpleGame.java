package com.anotsosimple.game;

import com.anotsosimple.game.ai.pathfinding.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class ANotSoSimpleGame extends ApplicationAdapter {
	// objects neeeded for rendering
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer mapRenderer;
	ShapeRenderer shapeRenderer;

	// objects neeeded for pathfinding
	TiledGraph tiledGraph;
	IndexedAStarPathFinder<TiledNode> pathFinder;
	DefaultGraphPath<TiledNode> path;
	TiledManhattanDistance manhattanDistance;
	
	@Override
	public void create () {
		// setup the camera
		camera = new OrthographicCamera(512, 256);
		camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0f);
		camera.update();

		// setup the tilemap
		TiledMap map = new TmxMapLoader().load("level01.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		mapRenderer.setView(camera);

		// setup the renderer for visual debug
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setAutoShapeType(true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.render();
	}
	
	@Override
	public void dispose () {
	}
}
