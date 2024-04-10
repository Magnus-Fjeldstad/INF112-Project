package inf112.skeleton.app.sprites.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.IEntity;

/**
 * Class representing the player in the game.
 */

public class PlayerModel extends Sprite implements IEntity{

    public PlayerEnum currentState;
    public PlayerEnum previousState;

    public Body b2body;
    public World world;

    public int health = 70;
    public int maxHealth = 100;
    public float movementSpeed = 4;

    public PlayerModel(PlayScreen screen) {
        this.world = screen.getWorld();
        currentState = PlayerEnum.STANDING;
        previousState = PlayerEnum.STANDING;

        // Set the player's health, speed and attack damage
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

        b2body.createFixture(fdef);
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
     * 
     * @param deltaSpeed the change in speed
     */
    public void setSpeed(float deltaSpeed) {
        this.movementSpeed += deltaSpeed;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int deltaMaxHealth) {
        this.maxHealth += deltaMaxHealth;
    }

    @Override
    public void update(float dt) {
        // TODO 
    }

    @Override
    public void dispose() {
        // TODO 
    }

}
