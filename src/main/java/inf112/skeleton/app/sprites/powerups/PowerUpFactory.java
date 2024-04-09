package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.screens.PlayScreen;


public class PowerUpFactory {


    private PlayScreen screen;

    public PowerUpFactory(PlayScreen screen) {
        this.screen = screen;
    }

    public AbstractPowerUp createPowerUp(PowerUpEnum type) {
        switch (type) {
            case SPEED_BOOST:
                return new SpeedPowerUp(screen);
            case DAMAGE_BOOST:
                return new DamagePowerUp(screen);
            default:
                throw new IllegalArgumentException("Invalid power-up type: " + type);
        }
    }
}