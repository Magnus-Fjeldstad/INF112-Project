package inf112.skeleton.app.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class WorldContactListener implements ContactListener {

    public Array<Body> bodiesToRemove = new Array<Body>();

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // Check if either fixture's UserData is "Fireball" and the other is "Wall"
        if ((fixA.getUserData() != null && fixA.getUserData().equals("Fireball") && fixB.getUserData() != null
                && fixB.getUserData().equals("Wall")) ||
                (fixA.getUserData() != null && fixA.getUserData().equals("Wall") && fixB.getUserData() != null
                        && fixB.getUserData().equals("Fireball"))) {

            // If fixA is the fireball, add its body to bodiesToRemove
            if (fixA.getUserData().equals("Fireball")) {
                bodiesToRemove.add(fixA.getBody());
            }
            // If fixB is the fireball, add its body to bodiesToRemove
            else if (fixB.getUserData().equals("Fireball")) {
                bodiesToRemove.add(fixB.getBody());
            }
        }
    }

    public Array<Body> getBodiesToRemove() {
        return bodiesToRemove;
    }

    public void setBodiesToRemove(Array<Body> bodiesToRemove) {
        this.bodiesToRemove = bodiesToRemove;
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
