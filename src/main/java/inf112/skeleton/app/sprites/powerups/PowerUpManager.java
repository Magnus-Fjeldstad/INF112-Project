package inf112.skeleton.app.sprites.powerups;



import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.PowerUpCollisionHandler;
import inf112.skeleton.app.sprites.player.PlayerModel;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.Random;


//This class is responsible for spawning powerups in the game
public class PowerUpManager {
    private final PowerUpFactory powerUpFactory;
    private final PlayerModel playerModel;
    private final ArrayList<AbstractPowerUp> powerUps;
    private final ArrayList<AbstractPowerUp> activePowerUps;
    private final PowerUpCollisionHandler powerUpCollisionHandler;
    private float timeSinceLastPowerUp;
    private static final float SPAWN_INTERVAL = 5;

    public PowerUpManager(PlayScreen screen, PlayerModel playerModel) {
        this.powerUps = new ArrayList<AbstractPowerUp>();
        this.activePowerUps = new ArrayList<AbstractPowerUp>();
        this.playerModel = playerModel;
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

        // Count down the duration of the active power-ups. No need to update the power ups that hasn't been touched
        Iterator<AbstractPowerUp> iterator = activePowerUps.iterator();
        while (iterator.hasNext()) {
            AbstractPowerUp powerUp = iterator.next();
            powerUp.update(dt);
            if (powerUp.isRemovable) {
                iterator.remove();
            }
        }


        handleCollision();

    }

    private void handleCollision() {
        Iterator<AbstractPowerUp> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            AbstractPowerUp powerUp = iterator.next();
            if (powerUpCollisionHandler.getBodiesToRemove().contains(powerUp.b2body, true)) {
                powerUp.applyPowerUp();
                activePowerUps.add(powerUp);
                iterator.remove();
            }
        }
        powerUpCollisionHandler.clearBodiesToRemove();
    }
    
    private AbstractPowerUp createRandomPowerUp() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        switch (i) {
            case 0:
                return powerUpFactory.createPowerUp(PowerUpEnum.SPEED_BOOST, playerModel);
            case 1:
                return powerUpFactory.createPowerUp(PowerUpEnum.DAMAGE_BOOST, playerModel);
            default:
                throw new IllegalArgumentException("Invalid powerup");
        }
    }

    public PowerUpCollisionHandler getPowerUpCollisionHandler() {
        return powerUpCollisionHandler;
    }

    public ArrayList<AbstractPowerUp> getPowerUps() {
        return powerUps;
    }

}
