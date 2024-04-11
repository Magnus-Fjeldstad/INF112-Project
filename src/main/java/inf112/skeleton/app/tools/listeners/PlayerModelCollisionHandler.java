package inf112.skeleton.app.tools.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.GameCreate;

public class PlayerModelCollisionHandler extends CollisionHandler {

    private Array<Body> playerHit;

    public PlayerModelCollisionHandler() {
        this.playerHit = new Array<>();
    }


    @Override
    public void handleCollision(Contact contact) {
        if (isCollisionBetween(contact, GameCreate.CATEGORY_PLAYER, GameCreate.CATEGORY_ENEMY)) {
            Fixture player = getFixtureByCategory(contact, GameCreate.CATEGORY_PLAYER);
            playerHit.add(player.getBody());
        }
    }

    @Override
    public Array<Body> getBodiesToRemove() {
        return playerHit;
    }

    @Override
    public void clearBodiesToRemove() {
        playerHit.clear();
    }
}