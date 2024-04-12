package inf112.skeleton.app.sprites.weapons.fireball;

import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.tools.listeners.FireballCollisionHandler;
import inf112.skeleton.app.tools.sound.SoundManager;

import java.util.Iterator;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

//This class is responsible for spawning powerups in the game
public class FireballManager {
    private final Array<Fireball> fireballs;
    private final FireballFactory fireballFactory;
    private final Array<Body> fireballsToRemove;
    private final FireballCollisionHandler fireballCollisionHandler;
    private float timeSinceLastPowerUp;
    private static final float SPAWN_INTERVAL = 2;
    private final PlayScreen screen;
    private final SoundManager soundManager;
    

    public FireballManager(PlayScreen screen) {
        this.fireballs = new Array<>();
        this.fireballsToRemove = new Array<>();
        this.screen = screen;
        this.fireballFactory = new FireballFactory(screen);
        fireballCollisionHandler = new FireballCollisionHandler();
        this.soundManager = new SoundManager();
    }

    public void update(float dt) {
        timeSinceLastPowerUp += dt;

        if (timeSinceLastPowerUp >= SPAWN_INTERVAL) {
            createConeFireball();
            timeSinceLastPowerUp = 0;
            soundManager.fireball_shoot.play();
        }

        for (Fireball fireball : fireballs) {
            fireball.update(dt);
        }

        handleCollision();

    }

    private void handleCollision() {
        Iterator<Fireball> iterator = fireballs.iterator();
        while (iterator.hasNext()) {
            Fireball fireball = iterator.next();
            if (fireballCollisionHandler.getBodiesToRemove().contains(fireball.b2body, true)) {
                fireballsToRemove.add(fireball.b2body);
                fireball.b2body.getWorld().destroyBody(fireball.b2body);
                iterator.remove();
            }
        }

        fireballCollisionHandler.clearBodiesToRemove();
    }

    private void createConeFireball() {
        for (int i = 0; i < 3; i++) {
            Fireball coneFireball = fireballFactory.createFireball();
            Vector2 direction = calculateVector();
            Vector2 coneVelocity = direction.cpy().rotateDeg(-15 + i * 15); // Adjust angle as needed
            coneFireball.setLinearVelocity(coneVelocity);
            fireballs.add(coneFireball);
        }
    }

    // Firing additional fireballs in eight directions
    // Automatic firing
    // for (int i = 0; i < 8; i++) {
    // Fireball directionFireball = new Fireball(this, 50,
    // atlas);
    // Vector2 directionVelocity = direction.cpy().setAngleDeg(i *
    // 45).nor().scl(speedMultiplier);
    // // velocity
    // directionFireball.setLinearVelocity(directionVelocity);
    // fireballs.add(directionFireball);
    // }

    private Vector2 calculateVector() {
        // Player position
        Vector2 playerPosition = new Vector2(screen.getPlayerModel().b2body.getPosition().x,
                screen.getPlayerModel().b2body.getPosition().y);

        // Cursor position
        Vector3 cursorPosition = screen.getCursorPosition();

        // Convert cursor position to game world coordinates
        screen.getGameCam().unproject(cursorPosition);

        //
        Vector2 direction = new Vector2(cursorPosition.x, cursorPosition.y).sub(playerPosition).nor();

        return direction;
    }

    public FireballCollisionHandler getFireballCollisionHandler() {
        return fireballCollisionHandler;
    }

    public Array<Fireball> getFireball() {
        return fireballs;
    }

}
