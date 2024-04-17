package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class DamagePowerUp extends AbstractPowerUp {

    private PlayerModel playerModel;

    public DamagePowerUp(PlayScreen screen, PlayerModel playerModel, int xPos, int yPos) {
        super(screen, playerModel, PowerUpEnum.DAMAGE_BOOST, xPos, yPos);
        this.playerModel = playerModel;
    }

    protected void removePowerUpEffect() {
        playerModel.attackDamage -= 5;
    }

    protected void applyPowerUpEffect() {
        playerModel.attackDamage += 5;
    }

}   
