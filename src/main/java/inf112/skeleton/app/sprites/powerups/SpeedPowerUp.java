package inf112.skeleton.app.sprites.powerups;


import inf112.skeleton.app.sprites.player.PlayerModel;

public class SpeedPowerUp extends AbstractPowerUp {

    private PowerUpEnum type = PowerUpEnum.SPEED_BOOST;
    private PlayerModel playerModel;

    public SpeedPowerUp(PlayerModel playerModel) {
        super(playerModel, PowerUpEnum.SPEED_BOOST);
    }


    protected void removePowerUpEffect() {
        playerModel.movementSpeed -= 4;
    }

    protected void applyPowerUpEffect() {
        playerModel.movementSpeed += 4;
    }

}   
