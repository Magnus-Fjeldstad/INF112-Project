package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GameTest;
import inf112.skeleton.app.controller.KeyHandler;
import inf112.skeleton.app.scenes.Hud;
import inf112.skeleton.app.sprites.Fireball;
import inf112.skeleton.app.sprites.PlayerModel;
import inf112.skeleton.app.tools.B2WorldCreator;

public class PlayScreen implements Screen {

    private GameTest game;

    // Gamecam + HUD variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    // Map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d variabels
    private World world;
    private Box2DDebugRenderer b2dr;

    // Sprites
    private PlayerModel player;

    // Variables for keyhandler
    private KeyHandler keyHandler;

    // Array of fireballs
    private Array<Fireball> fireballs;

    public PlayScreen(GameTest game) {
        this.game = game;
        // Create cam to follow the player
        gamecam = new OrthographicCamera();

        // create a FitViewPort mantain virtual asprect ratio
        gamePort = new FitViewport(GameTest.V_Width / GameTest.PPM, GameTest.V_Height / GameTest.PPM, gamecam);

        // create our game HUD
        hud = new Hud(game.batch);

        // Map Loader/ MapRenderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GameTest.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Creates the "world" and adds gravity
        world = new World(new Vector2(0, 0), true);

        b2dr = new Box2DDebugRenderer();

        // Creates the B2 world: The physics
        new B2WorldCreator(world, map);

        // Creates the player
        player = new PlayerModel(world, 100, 4, 5);

        // Creates a KeyHandler for he player
        keyHandler = new KeyHandler(player, this);

        // Creates an array of fireballs
        fireballs = new Array<Fireball>();

    }

    @Override
    public void show() {
    }

    /**
     * Updated the game
     * 
     * @param dt is the games "clock" the game updates based on the
     *           deltatime
     */
    public void update(float dt) {
        keyHandler.handleInput(dt);

        world.step(1 / 60f, 6, 2);

        // updates the gamecam
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin(); 

        for (Fireball fireball : fireballs) {
            fireball.draw(game.batch);
        }

        game.batch.end(); 

        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void createFireball() {
        Fireball newFireball = new Fireball(player, world);
        fireballs.add(newFireball);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

}