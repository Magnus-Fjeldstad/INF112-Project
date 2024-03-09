package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.PlayerModel;

public class SpeedPowerUp extends AbstractPowerUp {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public SpeedPowerUp(PlayScreen screen, float startingX, float startingY, TextureAtlas.AtlasRegion region) {
        super(screen, startingX, startingY, region); // Initialize the Sprite with the given region
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(startingX, startingY);
        definePowerUp();
    }

    private void startTimer() {
        super.startPowerUp();
    }

    private void removePowerUp() {
        // Remove the powerup from the game
    }

    private void applyPowerUp() {
        // Apply the powerup to the player
    }

    protected void definePowerUp() {
        // Define the powerup
    }

    public void update(float dt) {
        // Update the powerup
    }

    
}   
