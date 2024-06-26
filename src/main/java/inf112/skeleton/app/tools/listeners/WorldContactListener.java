package inf112.skeleton.app.tools.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.skeleton.app.GameCreate;

public class WorldContactListener implements ContactListener {
    private final PowerUpCollisionHandler powerUpCollisionHandler;
    private final FireballCollisionHandler fireballCollisionHandler;
    private final EnemyCollisionHandler enemyCollisionHandler;
    private final PlayerModelCollisionHandler playerCollisionHandler;

    public WorldContactListener(PowerUpCollisionHandler powerUpCollisionHandler,
            FireballCollisionHandler fireballCollisionHandler, EnemyCollisionHandler enemyCollisionHandler,
            PlayerModelCollisionHandler playerCollisionHandler) {
        this.powerUpCollisionHandler = powerUpCollisionHandler;
        this.fireballCollisionHandler = fireballCollisionHandler;
        this.enemyCollisionHandler = enemyCollisionHandler;
        this.playerCollisionHandler = playerCollisionHandler;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Delegate to PowerUpCollisionHandler if a power-up is involved in the
        // collision
        if (isCollisionBetween(contact, GameCreate.CATEGORY_PLAYER, GameCreate.CATEGORY_POWERUP)) {
            powerUpCollisionHandler.handleCollision(contact);
        }

        // Delegate to FireballCollisionHandler if a fireball is involved in the
        // collision
        // Replace Fireball.class with the actual class of your fireballs
        if (isCollisionBetween(contact, GameCreate.CATEGORY_FIREBALL, GameCreate.CATEGORY_WALLS)) {
            fireballCollisionHandler.handleCollision(contact);
        }

        if (isCollisionBetween(contact, GameCreate.CATEGORY_FIREBALL, GameCreate.CATEGORY_ENEMY)) {
            enemyCollisionHandler.handleCollision(contact);
            fireballCollisionHandler.handleCollision(contact);
        }

        if (isCollisionBetween(contact, GameCreate.CATEGORY_PLAYER, GameCreate.CATEGORY_ENEMY)) {
            playerCollisionHandler.handleCollision(contact);
            System.out.println("Player collided with enemy");
        }

        // Add more conditions here to delegate to other handlers...
    }

    protected boolean isCollisionBetween(Contact contact, short category1, short category2) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        return (fixA.getUserData() != null && fixA.getUserData().equals(category1) && fixB.getUserData() != null
                && fixB.getUserData().equals(category2)) ||
                (fixA.getUserData() != null && fixA.getUserData().equals(category2) && fixB.getUserData() != null
                        && fixB.getUserData().equals(category1));
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
