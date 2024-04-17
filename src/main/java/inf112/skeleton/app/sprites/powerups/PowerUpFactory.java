package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;


public class PowerUpFactory {

    private PlayScreen screen;

    public PowerUpFactory(PlayScreen screen) {
        this.screen = screen;
    }

    public AbstractPowerUp createPowerUp(PowerUpEnum type, PlayerModel playerModel, int xPos, int yPos) {
        switch (type) {
            case SPEED_BOOST:
                return new SpeedPowerUp(screen, playerModel, xPos, yPos);
            case DAMAGE_BOOST:
                return new DamagePowerUp(screen, playerModel, xPos, yPos);
            default:
                throw new IllegalArgumentException("Invalid power-up type: " + type);
        }
    }
}