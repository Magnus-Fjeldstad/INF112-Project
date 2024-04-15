package inf112.skeleton.app.tools.listeners;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.GameCreate;

public class PlayerModelCollisionHandler extends CollisionHandler {

    private boolean isHit;


    public PlayerModelCollisionHandler() {
        isHit= false;
    }


    @Override
    public void handleCollision(Contact contact) {
        if (isCollisionBetween(contact, GameCreate.CATEGORY_PLAYER, GameCreate.CATEGORY_ENEMY)) {
            isHit = true;
        } 
    }

    @Override
    public Array<Body> getBodiesToRemove() {
        return null;
    }

    public boolean getIsHit(){
        return isHit;
    }

    public void setIsHitFalse(){
        isHit = false;
    }

    @Override
    public void clearBodiesToRemove() {
    }
}
