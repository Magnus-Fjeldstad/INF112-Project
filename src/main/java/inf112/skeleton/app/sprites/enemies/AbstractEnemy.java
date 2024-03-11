package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.screens.PlayScreen;

public abstract class AbstractEnemy extends Sprite  {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    public int health;
    public int attackDamage;
    public float movementSpeed;

    public AbstractEnemy(PlayScreen screen, float startingX, float startingY, int health, float movementSpeed, int attackDamage, TextureAtlas.AtlasRegion region) {
        super(region); // Initialize the Sprite with the given region
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(startingX, startingY);
        defineEnemy();
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        velocity = new Vector2(-(this.movementSpeed), -(this.movementSpeed)*2);
    }

    protected abstract void defineEnemy();
    public abstract void update(float dt);

    public void takeDamage(int damageTaken) {
        health -= damageTaken;
    }

    /**
     * Returns the AbstractEnemy's health
     * @return
     */
    public int getHealth() {
        return health;
    }
    
}   
