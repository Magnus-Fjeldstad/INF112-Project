package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class DamagePowerUp extends AbstractPowerUp {
    private TextureRegion sprite;

    public DamagePowerUp(PlayScreen screen, PlayerModel playerModel) {
        super(screen,playerModel, screen.getAtlas().findRegion("SkeletonEnemy"));
        sprite = new TextureRegion(getTexture(), 2, 2, 14, 18);
        setRegion(sprite);
    }

    protected void removePowerUpEffect() {
        // Remove the powerup from the game
    }

    protected void applyPowerUpEffect() {
        // Apply the powerup to the player
    }

}   
