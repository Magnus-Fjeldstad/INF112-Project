package inf112.skeleton.app.enteties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    /**
     * Movement velocity for the player sprite
     */
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 2;
    
    public Player (Sprite sprite) {
        super(sprite);
    }
    

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    public void update(float delta) {
        setX(getX() + velocity.x * delta);
        setY(getY() + velocity.y * delta);
    }
    
}
