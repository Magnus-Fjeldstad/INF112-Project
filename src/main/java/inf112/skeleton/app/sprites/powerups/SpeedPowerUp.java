package inf112.skeleton.app.sprites.powerups;


import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class SpeedPowerUp extends AbstractPowerUp {

    private PlayerModel playerModel;

    public SpeedPowerUp(PlayScreen screen, PlayerModel playerModel, int xPos, int yPos) {
        super(screen, playerModel, PowerUpEnum.SPEED_BOOST, xPos, yPos);
        this.playerModel = playerModel;
    }


    protected void removePowerUpEffect() {
        playerModel.setMaxSpeed(-.5f);
        playerModel.setSpeed(-.5f);
    }

    protected void applyPowerUpEffect() {
        playerModel.setMaxSpeed(+.5f);
        playerModel.setSpeed(+.5f);
    }
}   
