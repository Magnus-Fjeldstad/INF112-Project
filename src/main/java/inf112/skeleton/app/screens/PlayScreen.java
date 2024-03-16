package inf112.skeleton.app.screens;

import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
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
import inf112.skeleton.app.sprites.enemies.AbstractEnemy;
import inf112.skeleton.app.sprites.enemies.AbstractEnemyFactory;
import inf112.skeleton.app.sprites.player.PlayerModel;
import inf112.skeleton.app.sprites.player.PlayerView;
import inf112.skeleton.app.sprites.powerups.PowerUpFactory;

import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayScreen implements Screen {
    private TextureAtlas atlas;

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

    // Array of fireballs
    private Array<Fireball> fireballs;

    // Fireball variables
    private float fireballCooldown = 1.5f;
    private float timeSinceLastFireball = 0f;
    private float speedMultiplier = 3.0f;

    // Array of enemies
    private Array<AbstractEnemy> enemies;

    private AbstractEnemyFactory enemyFactory;

    // ContactListener
    private WorldContactListener contactListener;

    private PowerUpFactory powerUpFactory;

    public PlayScreen(GameCreate game) {
        atlas = new TextureAtlas("Player_and_enemy.atlas");

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
        player = new PlayerModel(this, 70, 100, 4);
        playerView = new PlayerView(this, player);
        shapeRenderer = new ShapeRenderer();
        
        // Creates a KeyHandler for the player
        keyHandler = new KeyHandler(player, game, this);

        // Creates an array of fireballs
        fireballs = new Array<Fireball>(1000);

        enemies = new Array<AbstractEnemy>();

        enemyFactory = new AbstractEnemyFactory(this);

        contactListener = new WorldContactListener();

        world.setContactListener(contactListener);

        enemies.add(enemyFactory.spawnRandom());

        powerUpFactory = new PowerUpFactory(this);
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
        removeBodies(world);

        // System.out.println("Number of fireballs: " + fireballs.size);
        // Updated the player sprites position
        playerView.update(dt);

        // Updates the fireballs
        for (Fireball fireball : fireballs) {
            fireball.update(dt);
        }

        for (AbstractEnemy enemy : enemies) {
            enemy.update(dt);
        }

        attemptToFireFireball(dt);

        // updates the gamecam
        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;

        setCrosshairCursor();

        gamecam.update();
        renderer.setView(gamecam);
    }



    private void removeBodies(World world){
        Array<Body> bodiesToRemove = contactListener.getBodiesToRemove();
        for (Body body : bodiesToRemove) {
            if (body.getUserData() instanceof Fireball) {
                fireballs.removeValue((Fireball) body.getUserData(), true);
            } 
            world.destroyBody(body);
            contactListener.removeBodies();
        }
    }
    


    

    //To implement
    private void removePowerUp(Body body){

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
       

        for (Fireball fireball : fireballs) {
            fireball.draw(game.batch);
        }

        removeDeadEnemies();
        for (AbstractEnemy enemy : enemies) {
            enemy.draw(game.batch);
        }
        powerUpFactory.createRandomPowerUp().draw(game.batch);

        game.batch.end();

        shapeRenderer.setProjectionMatrix(gamecam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        playerView.drawHealthBar(shapeRenderer);
        shapeRenderer.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }



    /**
     * @param direction spawns a fireball at the players center
     *                  and directs it in the direction of the players cursor
     */
    private void createFireball(Vector2 direction) {
        // Fireball newFireball = new Fireball(this, player.getAttackDamage(), atlas);
        // newFireball.setLinearVelocity(direction);
        // fireballs.add(newFireball);

        //Firing additional fireballs in a cone
        for (int i = 0; i < 3; i++) {
            Fireball coneFireball = new Fireball(this, 50, atlas);
            Vector2 coneVelocity = direction.cpy().rotateDeg(-15 + i * 15); // Adjust angle as needed
            coneFireball.setLinearVelocity(coneVelocity);
            fireballs.add(coneFireball);
        }

        // Firing additional fireballs in eight directions
        // Automatic firing
        // for (int i = 0; i < 8; i++) {
        //     Fireball directionFireball = new Fireball(this, 50,
        //             atlas);
        //     Vector2 directionVelocity = direction.cpy().setAngleDeg(i *
        //             45).nor().scl(speedMultiplier);
        //     // velocity
        //     directionFireball.setLinearVelocity(directionVelocity);
        //     fireballs.add(directionFireball);
        // }
    }

    /**
     * 
     * @param dt attempts to fire a fireball every dt
     */
    private void attemptToFireFireball(float dt) {
        timeSinceLastFireball += dt;
        if (timeSinceLastFireball >= fireballCooldown) {
            timeSinceLastFireball = 0f;
            fireAutomaticFireball();
        }
    }
    /**
     * Fires a fireball in the direction of the cursor
     * 
     * @param screen The PlayScreen instance
     */
    private void fireAutomaticFireball() {
        // Get the player's position
        Vector2 playerPosition = new Vector2(player.b2body.getPosition().x, player.b2body.getPosition().y);

        // Get the cursor position in screen coordinates
        Vector3 cursorPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        // Convert screen coordinates to world coordinateswd
        Vector3 worldCursorPos = new Vector3(cursorPos);
        this.getGamecam().unproject(worldCursorPos);

        // Convert cursor position to vector
        Vector2 cursorPosition = new Vector2(worldCursorPos.x, worldCursorPos.y);

        // Calculate the direction vector (from player to cursor)
        Vector2 direction = new Vector2(cursorPosition).sub(playerPosition).nor();

        // Call the createFireball method with the calculated direction
        createFireball(direction);
    }

    /**
     * 
     * @return the gamecam
     */
    public OrthographicCamera getGamecam() {
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

    /**
     * Removes AbstractEnemies with health that is not 1 or more
     */
    private void removeDeadEnemies() {
        Array<AbstractEnemy> livingEnemies = new Array<AbstractEnemy>();
        for (AbstractEnemy enemy : enemies) {
            if (enemy.getHealth() > 0) {
                livingEnemies.add(enemy);
            } else {
                // The enemy is dead, so remove its body from the world
                world.destroyBody(enemy.getBody()); // Assuming getBody() returns the Box2D body
            }
        }
        // Update the enemies list to only include living enemies
        this.enemies = livingEnemies;
    }
}