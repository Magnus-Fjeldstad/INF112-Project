package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.screens.PlayScreen;

public abstract class AbstractEnemy extends Sprite  {
    
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    public int health;
    public int attackDamage;
    public float movementSpeed;

    public AbstractEnemy(PlayScreen screen, float startingX, float startingY, int health, float movementSpeed, int attackDamage, TextureAtlas.AtlasRegion region) {
        super(region); // Initialize the Sprite with the given region
        this.screen = screen;
        setPosition(startingX, startingY);
        defineEnemy();
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        velocity = new Vector2(-(this.movementSpeed), -(this.movementSpeed) * 2);
    }

    /**
     * Defines the box 2d body of the 
     */
    protected abstract void defineEnemy();

    /**
     * Updates the position of the Sprite
     * @param dt
     */
    public abstract void update(float dt);

    /**
     * Deals damage to enemy
     * @param int the damage dealt to the enemy
     */
    public void takeDamage(int damageTaken) {
        this.health -= damageTaken;
    }

    /**
     * Returns the AbstractEnemy's health
     * @return
     */
    public int getHealth() {
        return this.health;
    }
    
}   
