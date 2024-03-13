package inf112.skeleton.app.sprites.powerups;

import java.util.Timer;
import java.util.TimerTask;
import inf112.skeleton.app.sprites.powerups.*;
import java.util.Random;

public class PowerUpManager {
    public PowerUpManager() {
        spawnPowerUp();
    }

    private void spawnPowerUp() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                
            }
        },5000, 5000);
    }
    
    
    /*private AbstractPowerUp randomPowerUp() {
        Random rand = new Random();
        int i =     rand.nextInt(2);
        switch (rand) {
            case 0:
                return new SpeedPowerUp();
            case 1:
                return new DamagePowerUp();
            default:
                throw new IllegalArgumentException("Invalid powerup");

    }*/



}
