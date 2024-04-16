package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class DamagePowerUp extends AbstractPowerUp {

    private PowerUpEnum type = PowerUpEnum.DAMAGE_BOOST;

    private PlayerModel playerModel;

    public DamagePowerUp(PlayerModel playerModel) {
        super(playerModel, PowerUpEnum.DAMAGE_BOOST);
    }

    protected void removePowerUpEffect() {
        playerModel.attackDamage -= 5;
    }

    protected void applyPowerUpEffect() {
        playerModel.attackDamage += 5;
    }

    public PowerUpEnum getType() {
        return type;
    }
}   
