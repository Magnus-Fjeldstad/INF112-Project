package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class DamagePowerUp extends AbstractPowerUp {
    private TextureRegion sprite;

    public DamagePowerUp(PlayScreen screen) {
        super(screen, screen.getAtlas().findRegion("SkeletonEnemy"));
        sprite = new TextureRegion(getTexture(), 2, 2, 14, 18);
        setRegion(sprite);
    }

    protected void removePowerUp(PlayerModel playerModel) {
        // Remove the powerup from the game
    }

    protected void applyPowerUp(PlayerModel playerModel) {
        // Apply the powerup to the player
    }

}   
