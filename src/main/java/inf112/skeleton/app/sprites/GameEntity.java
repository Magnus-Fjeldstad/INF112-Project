package inf112.skeleton.app.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Abstract class for defining a generic game entity with health, movement speed, and attack damage.
 */
public abstract class GameEntity extends Sprite {
    protected World world;
    public Body b2body;
    protected int health;
    protected float movementSpeed;
    protected int attackDamage;

    /**
     * Constructor for GameEntity.
     *
     * @param world The game world this entity belongs to.
     * @param health Initial health of the entity.
     * @param movementSpeed Movement speed of the entity.
     * @param attackDamage Attack damage of the entity.
     */
    public GameEntity(World world, int health, float movementSpeed, int attackDamage) {
        this.world = world;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        defineEntity();
    }

    /**
     * Abstract method to define the entity.
     */
    protected abstract void defineEntity();



    // Getters and setters for health, movement speed, and attack damage can be added as needed.
}

