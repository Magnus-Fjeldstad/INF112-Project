package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.player.PlayerModel;

public class SpeedPowerUp extends AbstractPowerUp {
    private TextureRegion sprite;

    public SpeedPowerUp(PlayScreen screen, PlayerModel playerModel) {
        super(screen, playerModel, screen.getAtlas().findRegion("SkeletonEnemy"));
    
        sprite = new TextureRegion(getTexture(), 2, 2, 14, 18);
        setRegion(sprite);
    }


    protected void removePowerUpEffect() {
        playerModel.movementSpeed -= 4;
    }

    protected void applyPowerUpEffect() {
        playerModel.movementSpeed += 4;
    }
}   
