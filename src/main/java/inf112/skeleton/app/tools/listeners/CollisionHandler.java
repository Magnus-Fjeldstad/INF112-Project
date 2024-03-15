package inf112.skeleton.app.tools.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact; 
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;



public abstract class CollisionHandler {

    public abstract void handleCollision(Contact contact);

   
    public abstract Array<Body> getBodiesToRemove();

    public abstract void clearBodiesToRemove();

    /**
     * 
     * @param contact
     * @param category1
     * @param category2
     * @return checks if fix is not null and if the
     */
    protected boolean isCollisionBetween(Contact contact, short category1, short category2) {
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
    protected Fixture getFixtureByCategory(Contact contact, short category) {
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


}