package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.PlayerModel;

/**
 * Class to handle keyboard input for controlling the player.
 */
public class KeyHandler {

    private PlayerModel player;

    private PlayScreen playScreen;


    /**
     * Constructor for KeyHandler.
     *
     * @param player The player model that will be controlled.
     */
    public KeyHandler(PlayerModel player, PlayScreen playScreen) {
        this.player = player;
        this.playScreen = playScreen;
    }

    /**
     * Handles keyboard input to control the player.
     *
     * @param dt Delta time for frame-based movement adjustments.
     */
    public void handleInput(float dt) {
        float force = player.getSpeed(); // Adjust the force based on your game's physics scale
        float stopForce = 5f; // Adjust the stop force as needed
        float maxSpeed = 2f; // Adjust the maximum speed as needed

        // Apply forces based on input
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            player.b2body.applyForceToCenter(0, force, true);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.b2body.applyForceToCenter(0, -force, true);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.b2body.applyForceToCenter(force, 0, true);
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.b2body.applyForceToCenter(-force, 0, true);

        // Apply stopping force when keys are released
        if (!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            float vy = player.b2body.getLinearVelocity().y;
            player.b2body.applyForceToCenter(0, -vy * stopForce, true);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float vx = player.b2body.getLinearVelocity().x;
            player.b2body.applyForceToCenter(-vx * stopForce, 0, true);
        }
        

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playScreen.createFireball();
        }
    


        // Limit maximum velocity
        float vx = MathUtils.clamp(player.b2body.getLinearVelocity().x, -maxSpeed, maxSpeed);
        float vy = MathUtils.clamp(player.b2body.getLinearVelocity().y, -maxSpeed, maxSpeed);
        
        player.b2body.setLinearVelocity(vx, vy);
    }
}
