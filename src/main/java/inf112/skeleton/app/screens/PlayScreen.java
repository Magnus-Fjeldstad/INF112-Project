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
import inf112.skeleton.app.tools.listeners.PlayerModelCollisionHandler;
import inf112.skeleton.app.tools.listeners.WorldContactListener;
import inf112.skeleton.app.sprites.weapons.fireball.Fireball;
import inf112.skeleton.app.sprites.weapons.fireball.FireballManager;
import inf112.skeleton.app.sprites.enemies.AbstractEnemy;
import inf112.skeleton.app.sprites.enemies.EnemyManager;
import inf112.skeleton.app.sprites.player.PlayerModel;
import inf112.skeleton.app.sprites.player.PlayerView;
import inf112.skeleton.app.sprites.powerups.AbstractPowerUp;
import inf112.skeleton.app.sprites.powerups.PowerUpManager;

import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayScreen implements Screen {
    private final TextureAtlas atlas;
    private final TextureAtlas enemyAtlas;
    private final TextureAtlas fireballAtlas;

    private final GameCreate game;

    // Gamecam + HUD variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private final Hud hud;

    // Map variables
    private final TmxMapLoader mapLoader;
    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    // Box2d variabels
    private final World world;
    private final Box2DDebugRenderer b2dr;

    // Sprites
    private final PlayerModel player;
    private final PlayerView playerView;
    private final ShapeRenderer shapeRenderer;

    // Variables for keyhandler
    private final KeyHandler keyHandler;

    // Array of enemies
    private final Array<AbstractEnemy> enemies;

    // Variables for powerups, weapons and enemies
    private final PowerUpManager powerUpManager;
    private final FireballManager fireballManager;
    private final EnemyManager enemyManager;

    private final PlayerModelCollisionHandler playerCollisionHandler;

    private final WorldContactListener worldContactListener;

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

        playerCollisionHandler = player.getPlayerModelCollisionHandler();

        enemies = enemyManager.getEnemies();

        worldContactListener = new WorldContactListener(powerUpManager.getPowerUpCollisionHandler(),
                fireballManager.getFireballCollisionHandler(), enemyManager.getEnemyCollisionHandler(), playerCollisionHandler);
        world.setContactListener(worldContactListener);
    }

    @Override
    public void show() {
    }

    /**
     * Updates the game
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

        player.update(dt);
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

        for (AbstractPowerUp powerUp : powerUpManager.getPowerUps()) {
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

    // To implement field variable
    public Vector3 getCursorPosition() {
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

    /**
     * Sets the crosshair for the screen
     */
    private void setCrosshairCursor() {
        Gdx.graphics.setSystemCursor(SystemCursor.Crosshair);
    }

    /**
     * @return TextureAtlas atlas
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * 
     * @return an array of the enemies
     */
    public Array<AbstractEnemy> getEnemies() {
        return enemies;
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
        atlas.dispose();
        enemyAtlas.dispose();
        fireballAtlas.dispose();
        gamecam = null;
        gamePort = null;
        shapeRenderer.dispose();
    }
}