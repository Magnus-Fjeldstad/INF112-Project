package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;


public class RedEnemy extends AbstractEnemy {

    public Body b2body;
    private TextureRegion enemyStand;

    /**
     * Constructor for the RedEnemy
     * @param screen
     * @param x
     * @param y
     * @param health
     * @param movementSpeed
     * @param damage
     */
    public RedEnemy(PlayScreen screen, float x, float y, int health, float movementSpeed, int damage) {
        super(screen, x, y, health, movementSpeed, damage, screen.getEnemyAtlas().findRegion("RedEnemy"));
        enemyStand = new TextureRegion(getTexture(), 32, 80, 16, 16);
        setBounds(2, 2, 14 / GameCreate.PPM, 18 / GameCreate.PPM);
        setRegion(enemyStand);
    }

    @Override
    public void update(float dt) {
        // Get the player's position
        Vector2 playerPosition = screen.getPlayerModel().b2body.getPosition();

        // Calculate the direction from enemy to player
        Vector2 direction = playerPosition.sub(getBody().getPosition()).nor();

        // Set the enemy's movement speed
        float speed = movementSpeed; // You should define this properly

        // Apply linear velocity to move towards the player
        getBody().setLinearVelocity(direction.scl(speed));

        // Set the enemy's position in the game world
        setPosition(getBody().getPosition().x - getWidth() / 2, getBody().getPosition().y - getHeight() / 2);
    }
}
