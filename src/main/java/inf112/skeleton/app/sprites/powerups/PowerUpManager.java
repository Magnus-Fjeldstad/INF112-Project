package inf112.skeleton.app.sprites.powerups;



import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.PowerUpCollisionHandler;
import inf112.skeleton.app.sprites.player.PlayerModel;
import inf112.skeleton.app.sprites.powerups.AbstractPowerUp;
import inf112.skeleton.app.sprites.powerups.AbstractPowerUpView;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.Random;


//This class is responsible for spawning powerups in the game
public class PowerUpManager {
    private final PowerUpFactory powerUpFactory;
    private PlayerModel playerModel;
    private final ArrayList<AbstractPowerUp> powerUps;
    private final ArrayList<AbstractPowerUp> activePowerUps;
    private final ArrayList<AbstractPowerUpView> powerUpViews;
    private final PowerUpCollisionHandler powerUpCollisionHandler;
    private float timeSinceLastPowerUp;
    private static final float SPAWN_INTERVAL = 5;

    public PowerUpManager(PlayScreen screen, PlayerModel playerModel) {
        this.powerUps = new ArrayList<AbstractPowerUp>();
        this.powerUpViews = new ArrayList<AbstractPowerUpView>();
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
            if (powerUp.getType() == PowerUpEnum.SPEED_BOOST) {
                powerUpViews.add(new SpeedPowerUpView(powerUp));
            } else {
                powerUpViews.add(new DamagePowerUpView(powerUp));
            }
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
        int xPos = rand.nextInt(32, GameCreate.V_Height);
        int yPos = rand.nextInt(32, GameCreate.V_Width);
        int i = rand.nextInt(2);
        switch (i) {
            case 0:
                AbstractPowerUp speedPowerUp = powerUpFactory.createPowerUp(PowerUpEnum.SPEED_BOOST, playerModel, xPos, yPos);
                return speedPowerUp;
            case 1:
                AbstractPowerUp damagPowerUp = powerUpFactory.createPowerUp(PowerUpEnum.DAMAGE_BOOST, playerModel, xPos, yPos);
                return damagPowerUp;
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

    public ArrayList<AbstractPowerUpView> getPowerUpViews() {
        return powerUpViews;
    }
}
