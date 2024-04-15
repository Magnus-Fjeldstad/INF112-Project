package inf112.skeleton.app.sprites.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.IEntity;
import inf112.skeleton.app.tools.listeners.PlayerModelCollisionHandler;

public class PlayerModel extends Sprite implements IEntity {

    public PlayerEnum currentState;
    public PlayerEnum previousState;
    public Body b2body;
    public World world;
    private PlayScreen screen;

    public int health = 70;
    public int maxHealth = 100;

    public int attackDamage = 10;
    public float movementSpeed = 4;

    private float timeAccumulator = 0; // Time accumulator for health regen
    public int healthRegen = 1; // Health regeneration rate per second

    private PlayerModelCollisionHandler playerModelCollisionHandler;


    public PlayerModel(PlayScreen screen) {
        this.world = screen.getWorld();
        currentState = PlayerEnum.STANDING;
        previousState = PlayerEnum.STANDING;

        this.screen = screen;
        playerModelCollisionHandler = new PlayerModelCollisionHandler();

        definePlayer();
    }

    /**
     * 
     * @return the current state of the player
     */
    public PlayerEnum getState() {
        float xVelocity = b2body.getLinearVelocity().x;
        float yVelocity = b2body.getLinearVelocity().y;
        float velocityThreshold = 0.1f; // Define a suitable threshold for your game

        boolean isMovingHorizontally = Math.abs(xVelocity) > Math.abs(yVelocity);
        boolean isMoving = Math.abs(xVelocity) > velocityThreshold || Math.abs(yVelocity) > velocityThreshold;

        if (!isMoving) {
            return PlayerEnum.STANDING;
        }

        if (isMovingHorizontally) {
            if (xVelocity > 0) {
                return PlayerEnum.RIGHT;
            } else if (xVelocity < 0) {
                return PlayerEnum.LEFT;
            }
        } else {
            if (yVelocity > 0) {
                return PlayerEnum.UP;
            } else if (yVelocity < 0) {
                return PlayerEnum.DOWN;
            }
        }

        return PlayerEnum.STANDING;
    }

    /**
     * Defines the player's body and fixture.
     */
    protected void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameCreate.PPM, 32 / GameCreate.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(11 / GameCreate.PPM);

        fdef.shape = shape;
        fdef.filter.categoryBits = GameCreate.CATEGORY_PLAYER;
        fdef.filter.maskBits = GameCreate.CATEGORY_WALLS | GameCreate.CATEGORY_ENEMY | GameCreate.CATEGORY_POWERUP;

        b2body.createFixture(fdef).setUserData(GameCreate.CATEGORY_PLAYER);
    }

    /**
     * 
     * @return the player's health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * 
     * @param deltaHealth the change in health
     */
    public void setHealth(int deltaHealth) {
        this.health += deltaHealth;

    }

    /**
     * 
     * @return the player's speed
     */
    public float getSpeed() {
        return this.movementSpeed;
    }

    /**
     * @param deltaSpeed the change in speed
     */
    public void setSpeed(float deltaSpeed) {
        this.movementSpeed += deltaSpeed;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Updates the maxHealth of the player by a given value
     * @param deltaMaxHealth the amount the maxHealth should be changed by
     */
    public void setMaxHealth(int deltaMaxHealth) {
        this.maxHealth += deltaMaxHealth;
    }

    /**
     * @return attackDamage
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttackDamage(int deltaAttackDamage) {
        this.attackDamage += deltaAttackDamage;
    }

    /**
     * Handles the collision between the player and the enemies
     * If the player is hit, the player's health is reduced by the enemy's attack damage
     */
    public void handleCollision() {
        
        int attackDamage = 0;
        
        if (screen.getEnemies().notEmpty()) {
            attackDamage = screen.getEnemies().first().attackDamage;
        }

        if (playerModelCollisionHandler.getIsHit()) {
            setHealth(-attackDamage);
            playerModelCollisionHandler.setIsHitFalse();
        }
    }

    @Override
    public void update(float dt) {
        timeAccumulator += dt;
        if (timeAccumulator >= 1.0) {
            if (health < maxHealth) {
                health += healthRegen; // Increment health by the regen rate
            }
            timeAccumulator -= 1.0; // Reset the accumulator
        }

        handleCollision();
    }

    @Override
    public void dispose() {
        // TODO
    }

    public int getHealthRegen() {
        return healthRegen;
    }

    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }

    /**
     * 
     * @return the player's collision handler
     */
    public PlayerModelCollisionHandler getPlayerModelCollisionHandler() {
        return playerModelCollisionHandler;
    }
}
