package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.controller.KeyHandler;
import inf112.skeleton.app.scenes.Hud;
import inf112.skeleton.app.tools.B2WorldCreator;
import inf112.skeleton.app.tools.listeners.WorldContactListener;
import inf112.skeleton.app.sprites.weapons.fireball.Fireball;
import inf112.skeleton.app.sprites.weapons.fireball.FireballManager;
import inf112.skeleton.app.sprites.enemies.AbstractEnemy;
import inf112.skeleton.app.sprites.enemies.AbstractEnemyFactory;
import inf112.skeleton.app.sprites.enemies.EnemyManager;
import inf112.skeleton.app.sprites.player.PlayerModel;
import inf112.skeleton.app.sprites.player.PlayerView;
import inf112.skeleton.app.sprites.powerups.AbstractPowerUp;
import inf112.skeleton.app.sprites.powerups.PowerUpManager;

import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayScreen implements Screen {
    private TextureAtlas atlas;
    private TextureAtlas enemyAtlas;
    private TextureAtlas fireballAtlas;

    private GameCreate game;

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
    private PlayerView playerView;
    private ShapeRenderer shapeRenderer;


    // Variables for keyhandler
    private KeyHandler keyHandler;

    // Array of enemies
    private Array<AbstractEnemy> enemies;

    private PowerUpManager powerUpManager;
    private FireballManager fireballManager;
    private EnemyManager enemyManager;

    private WorldContactListener worldContactListener;

    public PlayScreen(GameCreate game) {
        atlas = new TextureAtlas("Player_and_enemy.atlas");
        enemyAtlas = new TextureAtlas("enemies.atlas");
        fireballAtlas = new TextureAtlas("fireball.atlas");

        this.game = game;
        // Create cam to follow the player
        gamecam = new OrthographicCamera();

        // create a FitViewPort mantain virtual asprect ratio
        gamePort = new FitViewport(GameCreate.V_Width / GameCreate.PPM, GameCreate.V_Height / GameCreate.PPM, gamecam);

        // create our game HUD
        hud = new Hud(game.batch);

        // Map Loader/ MapRenderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / GameCreate.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // Creates the "world" and adds gravity
        world = new World(new Vector2(0, 0), true);

        b2dr = new Box2DDebugRenderer();

        // Creates the B2 world: The physics
        new B2WorldCreator(this);

        // Creates the player
        player = new PlayerModel(this);
        playerView = new PlayerView(this, player);
        shapeRenderer = new ShapeRenderer();
        
        // Creates a KeyHandler for the player
        keyHandler = new KeyHandler(player, game, this);


        powerUpManager = new PowerUpManager(this, player);
        fireballManager = new FireballManager(this);
        enemyManager = new EnemyManager(this);

        enemies = enemyManager.getEnemies();
    
        worldContactListener = new WorldContactListener(powerUpManager.getPowerUpCollisionHandler(), fireballManager.getFireballCollisionHandler(), enemyManager.getEnemyCollisionHandler());
        world.setContactListener(worldContactListener);
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
        
        world.step(1 / 60f, 6, 2);
        keyHandler.handleInput(dt);

        powerUpManager.update(dt);
        fireballManager.update(dt);
        enemyManager.update(dt);

        playerView.update(dt);

        for (AbstractEnemy enemy : enemies) {
            enemy.update(dt);
        }
        
        // updates the gamecam
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;

        setCrosshairCursor();

        gamecam.update();
        renderer.setView(gamecam);
    }




    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        // debug lines for Box2d
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        playerView.draw(game.batch);
       

        for (Fireball fireball : fireballManager.getFireball()) {
            fireball.draw(game.batch);
        }

        for (AbstractEnemy enemy : enemies) {
            enemy.draw(game.batch);
        }

        for(AbstractPowerUp powerUp : powerUpManager.getPowerUps()){
            powerUp.draw(game.batch);
        }

       
        game.batch.end();

        shapeRenderer.setProjectionMatrix(gamecam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        playerView.drawHealthBar(shapeRenderer);
        shapeRenderer.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    //To implement field variable
    public Vector3 getCursorPosition(){
        Vector3 cursorPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        return cursorPos;
    }

    /**
     * 
     * @return the gamecam
     */
    public OrthographicCamera getGameCam() {
        return this.gamecam;
    }

    /**
     * 
     * @return the wolrd
     */
    public World getWorld() {
        return this.world;
    }

    /**
     * 
     * @return the map
     */
    public TiledMap getMap() {
        return this.map;
    }

    /**
     * Returns player
     * 
     * @return PlayerModel
     */
    public PlayerModel getPlayerModel() {
        return this.player;
    }

    private void setCrosshairCursor() {
        Gdx.graphics.setSystemCursor(SystemCursor.Crosshair);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * @return TextureAtlas enemyAtlas
     */
    public TextureAtlas getEnemyAtlas() {
        return enemyAtlas;
    }

    /**
     * @return TextureAtlas fireballAtlas
     */
    public TextureAtlas getFireballAtlas() {
        return fireballAtlas;
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