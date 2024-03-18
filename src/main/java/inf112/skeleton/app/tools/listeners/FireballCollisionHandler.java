package inf112.skeleton.app.tools.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.GameCreate;

public class FireballCollisionHandler extends CollisionHandler{
    private Array<Body> bodiesToRemove;

    public FireballCollisionHandler() {
        this.bodiesToRemove = new Array<>();
    }

    public Array<Body> getBodiesToRemove() {
        return bodiesToRemove;
    }

    public void clearBodiesToRemove() {
        bodiesToRemove.clear();
    }

    @Override
    public void handleCollision(Contact contact) {
        System.out.println(isCollisionBetween(contact, GameCreate.CATEGORY_FIREBALL, GameCreate.CATEGORY_WALLS));
        if (isCollisionBetween(contact, GameCreate.CATEGORY_FIREBALL, GameCreate.CATEGORY_WALLS)) {
            Fixture fireball = getFixtureByCategory(contact, GameCreate.CATEGORY_FIREBALL);
            bodiesToRemove.add(fireball.getBody());
        }
    }
}