package inf112.skeleton.app.sprites.powerups;

import java.util.Timer;
import java.util.TimerTask;

import inf112.skeleton.app.screens.PlayScreen;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


//This class is responsible for spawning powerups in the game
public class PowerUpFactory {
    private List<AbstractPowerUp> powerUps;
    private float timeSinceLastPowerUp;
    private static final float SPAWN_INTERVAL = 30;
    private PlayScreen screen;

    public PowerUpFactory(PlayScreen screen) {
        this.powerUps = new ArrayList<>();
        this.screen = screen;
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
    
    private AbstractPowerUp createRandomPowerUp() {
        Random rand = new Random();
        int i = rand.nextInt(2);
        switch (i) {
            case 0:
                return new SpeedPowerUp(screen);
            case 1:
                return new DamagePowerUp(screen);
            default:
                throw new IllegalArgumentException("Invalid powerup");
            }
        }
}
}
