package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.IEntity;
import com.badlogic.gdx.physics.box2d.World;

public abstract class AbstractEnemy extends Sprite implements IEntity{
    
    protected PlayScreen screen;
    private final World world;
    public Body b2body;
    public Vector2 velocity;
    public int health;
    public int attackDamage;
    public float movementSpeed;

    /**
     * Constructor for AbstractEnemy
     * @param screen
     * @param startingX
     * @param startingY
     * @param health
     * @param movementSpeed
     * @param attackDamage
     * @param region
     */
    public AbstractEnemy(PlayScreen screen, float startingX, float startingY, int health, float movementSpeed, int attackDamage, TextureAtlas.AtlasRegion region) {
        super(region); // Initialize the Sprite with the given region
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(startingX, startingY);
        defineEnemy(startingX, startingY);
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        velocity = new Vector2(-(this.movementSpeed), -(this.movementSpeed) * 2);
    }

    /**
     * Defines the box 2d body of the 
     */   
    private void defineEnemy(float startingX, float startingY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startingX / GameCreate.PPM, startingY/ GameCreate.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = screen.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameCreate.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameCreate.CATEGORY_ENEMY; 
        fixtureDef.filter.maskBits = GameCreate.CATEGORY_WALLS | GameCreate.CATEGORY_FIREBALL | GameCreate.CATEGORY_PLAYER | GameCreate.CATEGORY_ENEMY;

        b2body.createFixture(fixtureDef);
        b2body.createFixture(fixtureDef).setUserData(GameCreate.CATEGORY_ENEMY);
    }

    /**
     * Updates the position of the Sprite
     * @param dt
     */
    public abstract void update(float dt);

    /**
     * Returns the AbstractEnemy's health
     * @return
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the b2body of the AbstractEnemy
     * @return
     */
    public Body getBody() {
        return this.b2body;
    }

    /**
     * Sets the health of an AbstractEnemy
     * @param deltaHealt
     */
    public void setHealth(int deltaHealt) {
        this.health += deltaHealt;
    }
    
    /**
     * Removes the b2body from the screens world
     */
    public void dispose() {
        world.destroyBody(b2body);
    }

    public int getAttackDamage() {
        return this.health;
    }
}   
