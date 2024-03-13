package inf112.skeleton.app.sprites.powerups;

import java.util.Timer;
import java.util.TimerTask;

import inf112.skeleton.app.screens.PlayScreen;
import java.util.Random;


//This class is responsible for spawning powerups in the game
public class PowerUpFactory {
    private PlayScreen screen;

    public PowerUpFactory(PlayScreen screen) {
        this.screen = screen;
        spawnPowerUp();
    }

    private void spawnPowerUp() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                randomPowerUp();
            }
        },1000, 1000);
    }
    
    
    private AbstractPowerUp randomPowerUp() {
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