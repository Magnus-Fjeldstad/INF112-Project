package inf112.skeleton.app.tools.listeners;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;





public class WorldContactListener implements ContactListener {
    private Array<CollisionHandler> collisionHandlers;
    private CollisionHandler fireballCollisionHandler;
    private CollisionHandler powerUpCollisionHandler;

    public WorldContactListener() {
        this.collisionHandlers = new Array<CollisionHandler>();
        this.fireballCollisionHandler = new FireballCollisionHandler();
        //this.powerUpCollisionHandler = new PowerUpCollisionHandler();
        this.collisionHandlers.add(fireballCollisionHandler);
        //this.collisionHandlers.add(powerUpCollisionHandler);
    }

    @Override
    public void beginContact(Contact contact) {
        for (CollisionHandler handler : collisionHandlers) {
            handler.handleCollision(contact);
        }
    }

    public Array<Body> getBodiesToRemove() {
        Array<Body> allBodiesToRemove = new Array<>();
        for (CollisionHandler handler : collisionHandlers) {
            Array<Body> bodiesToRemove = handler.getBodiesToRemove();
            if (bodiesToRemove != null) {
                allBodiesToRemove.addAll(bodiesToRemove);
            }
        }
        return allBodiesToRemove;
    }

    public void removeBodies() {
        for (CollisionHandler handler : collisionHandlers) {
            handler.clearBodiesToRemove();
        }
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
