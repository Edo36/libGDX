package com.rpg.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.rpg.game.systems.AISystem;
import com.rpg.game.systems.AnimationSystem;
import com.rpg.game.systems.CameraSystem;
import com.rpg.game.systems.DebugRenderSystem;
import com.rpg.game.systems.MovementSystem;
import com.rpg.game.systems.PlayerSystem;
import com.rpg.game.systems.RenderSystem;
import com.rpg.game.systems.StateSystem;

/**
 * Created by Andrei on 29.07.2016.
 */
public class PlayScreen extends ScreenAdapter {
    RPG game;
	
	static final int PAUSED = 0;
	static final int RUNNING = 1;
	static final int FINISHED = 3;
	static final int GAME_OVER = 4;
	
	public static Vector3 inputLocation;

    OrthographicCamera camera;
	
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private Engine engine;
    private World world;

    public static Polygon collisionPoly;

    private boolean debug = false;

    public PlayScreen(RPG game) {
        inputLocation = new Vector3();

        Assets.load();

        this.game = game;

        camera = new OrthographicCamera(256, 144);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

		// move map render stuff to Assets
        map = new TmxMapLoader().load("level01.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        MapLayer collision = map.getLayers().get("collision");
        for(MapObject o: collision.getObjects()) {
            PolygonMapObject polyObject = (PolygonMapObject) o;
            collisionPoly = polyObject.getPolygon();
        }


        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        engine = new Engine();

        engine.addSystem(new StateSystem());
        //engine.addSystem(new PlayerSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new DebugRenderSystem(camera));
        engine.addSystem(new CameraSystem());
        engine.addSystem(new RenderSystem(batch, camera));
        engine.addSystem(new AISystem());
        engine.addSystem(new PlayerSystem());

        //engine.addSystem(new BehaviourSystem());


        //engine.getSystem(DebugRenderSystem.class).setProcessing(false);

        world = new World(engine);
        world.create();
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
            camera.unproject(inputLocation.set(Gdx.input.getX(), Gdx.input.getY(), 0f));

        // debug toggle
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            if(debug) {
            engine.getSystem(DebugRenderSystem.class).setProcessing(false);
            engine.getSystem(RenderSystem.class).setProcessing(true);
            debug = false;
            }

            else {
                engine.getSystem(DebugRenderSystem.class).setProcessing(true);
                engine.getSystem(RenderSystem.class).setProcessing(false);
                debug = true;
            }
        }

        mapRenderer.setView(camera);
        mapRenderer.render();

        engine.update(deltaTime);

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {

    }
}
