package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;


public class PowerUpFactory {

    private PlayScreen screen;

    public PowerUpFactory(PlayScreen screen) {
        this.screen = screen;
    }

    public AbstractPowerUp createPowerUp(PowerUpEnum type, PlayerModel playerModel) {
        switch (type) {
            case SPEED_BOOST:
                return new SpeedPowerUp(playerModel);
            case DAMAGE_BOOST:
                return new DamagePowerUp(playerModel);
            default:
                throw new IllegalArgumentException("Invalid power-up type: " + type);
        }
    }
}