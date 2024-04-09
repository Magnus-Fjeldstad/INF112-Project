package inf112.skeleton.app.sprites.powerups;



import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.PowerUpCollisionHandler;
import inf112.skeleton.app.sprites.powerups.PowerUpFactory;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import java.util.Random;


//This class is responsible for spawning powerups in the game
public class PowerUpManager {
    private PowerUpFactory powerUpFactory;
    private Array<AbstractPowerUp> powerUps;
    private Array<Body> powerUpsToRemove;
    private PowerUpCollisionHandler powerUpCollisionHandler;
    private float timeSinceLastPowerUp;
    private static final float SPAWN_INTERVAL = 2;
    private PlayScreen screen;

    public PowerUpManager(PlayScreen screen) {
        this.powerUps = new Array<>();
        this.powerUpsToRemove = new Array<>();
        this.screen = screen;
        this.powerUpFactory = new PowerUpFactory(screen);
        this.powerUpCollisionHandler = new PowerUpCollisionHandler();
    }
    
    public void update(float dt) {
        timeSinceLastPowerUp += dt;

        if (timeSinceLastPowerUp >= SPAWN_INTERVAL) {
            AbstractPowerUp powerUp = createRandomPowerUp();
            powerUps.add(powerUp);
            timeSinceLastPowerUp = 0;
        }

        for (AbstractPowerUp powerUp : powerUps) {
            powerUp.update(dt);
        }

        handleCollision();

    }

    private void handleCollision() {
        for (AbstractPowerUp powerUp : powerUps) {
            if (powerUpCollisionHandler.getBodiesToRemove().contains(powerUp.b2body, true)) {
                powerUps.removeValue(powerUp, false);
                powerUpsToRemove.add(powerUp.b2body);
                powerUp.b2body.getWorld().destroyBody(powerUp.b2body);
            }
        }

        powerUpCollisionHandler.clearBodiesToRemove();
    }
    
    private AbstractPowerUp createRandomPowerUp() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        switch (i) {
            case 0:
                return powerUpFactory.createPowerUp(PowerUpEnum.SPEED_BOOST);
            case 1:
                return powerUpFactory.createPowerUp(PowerUpEnum.DAMAGE_BOOST);
            default:
                throw new IllegalArgumentException("Invalid powerup");
        }
    }

    public PowerUpCollisionHandler getPowerUpCollisionHandler() {
        return powerUpCollisionHandler;
    }

    public Array<AbstractPowerUp> getPowerUps() {
        return powerUps;
    }

}
