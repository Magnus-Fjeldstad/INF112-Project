package inf112.skeleton.app.tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.app.GameCreate;

public class WorldContactListener implements ContactListener {

    public Array<Body> bodiesToRemove = new Array<Body>(1000);

    @Override
    public void beginContact(Contact contact) {

        if (isCollisionBetween(contact, GameCreate.CATEGORY_FIREBALL, GameCreate.CATEGORY_WALLS)) {
            Fixture fireball = getFixtureByCategory(contact, GameCreate.CATEGORY_FIREBALL);
            bodiesToRemove.add(fireball.getBody());
        }

        
    }

    /**
     * 
     * @return the array of bodies to remove
     */
    public Array<Body> getBodiesToRemove() {
        return bodiesToRemove;
    }

    /**
     * 
     * @param contact
     * @param category1
     * @param category2
     * @return checks if fix is not null and if the
     */
    private boolean isCollisionBetween(Contact contact, short category1, short category2) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
    
        return (fixA.getUserData() != null && fixA.getUserData().equals(category1) && fixB.getUserData() != null && fixB.getUserData().equals(category2)) ||
               (fixA.getUserData() != null && fixA.getUserData().equals(category2) && fixB.getUserData() != null && fixB.getUserData().equals(category1));
    }

    /**
     * 
     * @param contact
     * @param category
     * @return checks which fixture is the target category
     */
    private Fixture getFixtureByCategory(Contact contact, short category) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
    
        if (fixA.getUserData() != null && fixA.getUserData().equals(category)) {
            return fixA;
        } else if (fixB.getUserData() != null && fixB.getUserData().equals(category)) {
            return fixB;
        } else {
            return null;
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
