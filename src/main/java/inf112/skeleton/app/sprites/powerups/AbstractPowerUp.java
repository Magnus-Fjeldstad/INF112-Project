package inf112.skeleton.app.sprites.powerups;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


import inf112.skeleton.app.screens.PlayScreen;

public abstract class AbstractPowerUp extends Sprite  {
    protected World world;
    protected PlayScreen screen;
    private int startingX;
    private int startingY;
    public Body b2body;

    public AbstractPowerUp(PlayScreen screen, TextureAtlas.AtlasRegion region) {
        super(region); // Initialize the Sprite with the given region
        this.world = screen.getWorld();
        this.screen = screen;
        randomCoordinates();
        setPosition(startingX, startingY);
        definePowerUp();
    }

    protected void startPowerUp() {
        applyPowerUp();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removePowerUp();
            }
        }, 5000);

    }

    protected void randomCoordinates() {
        Random rand = new Random();
        startingX = rand.nextInt(100);
        startingY = rand.nextInt(100);
    }

    private void removePowerUp() {
        // Remove the powerup from the game
    }

    private void applyPowerUp() {
        // Apply the powerup to the player
    }

    protected abstract void definePowerUp();
    public abstract void update(float dt);
    
}   
