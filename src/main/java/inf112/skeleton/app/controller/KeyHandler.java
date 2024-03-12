package inf112.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.PlayerModel;

/**
 * Class to handle keyboard input for controlling the player.
 */
public class KeyHandler {

    private PlayerModel player;

    /**
     * Constructor for KeyHandler.
     *
     * @param player The player model that will be controlled.
     */
    public KeyHandler(PlayerModel player) {
        this.player = player;
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
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            player.b2body.applyForceToCenter(0, force, true);
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            player.b2body.applyForceToCenter(0, -force, true);

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            player.b2body.applyForceToCenter(force, 0, true);
        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            player.b2body.applyForceToCenter(-force, 0, true);

        // Apply stopping force when keys are released
        if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            float vy = player.b2body.getLinearVelocity().y;
            player.b2body.applyForceToCenter(0, -vy * stopForce, true);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            float vx = player.b2body.getLinearVelocity().x;
            player.b2body.applyForceToCenter(-vx * stopForce, 0, true);
        }

        // Limit maximum velocity
        float vx = MathUtils.clamp(player.b2body.getLinearVelocity().x, -maxSpeed, maxSpeed);
        float vy = MathUtils.clamp(player.b2body.getLinearVelocity().y, -maxSpeed, maxSpeed);

        player.b2body.setLinearVelocity(vx, vy);
    }
}
