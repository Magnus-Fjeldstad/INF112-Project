package inf112.skeleton.app.tools.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.app.GameCreate;

public class EnemyCollisionHandler extends CollisionHandler{
    private Array<Body> bodiesToRemove;

    public EnemyCollisionHandler() {
        this.bodiesToRemove = new Array<>();
    }

    @Override
    public void handleCollision(Contact contact) {
        if (isCollisionBetween(contact,  GameCreate.CATEGORY_ENEMY, GameCreate.CATEGORY_FIREBALL)) {
            Fixture enemy = getFixtureByCategory(contact, GameCreate.CATEGORY_ENEMY);
            bodiesToRemove.add(enemy.getBody());
        }
    }

    @Override
    public Array<Body> getBodiesToRemove() {
        return bodiesToRemove;
    }

    @Override
    public void clearBodiesToRemove() {
        bodiesToRemove.clear();
    }
}
