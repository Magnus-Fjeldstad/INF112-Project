package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class DamagePowerUp extends AbstractPowerUp {
    private final Texture sprite;

    public DamagePowerUp(PlayScreen screen, PlayerModel playerModel) {
        super(screen,playerModel, screen.getAtlas().findRegion("SkeletonEnemy"));

        sprite = new Texture("powerups/muscle.png");
        setRegion(sprite); 
    }

    protected void removePowerUpEffect() {
        playerModel.attackDamage -= 5;
    }

    protected void applyPowerUpEffect() {
        playerModel.attackDamage += 5;
    }

}   
