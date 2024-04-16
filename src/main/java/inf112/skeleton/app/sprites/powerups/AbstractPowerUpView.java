package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.g2d.Sprite;


public abstract class AbstractPowerUpView extends Sprite{

    private AbstractPowerUp powerUp;

    public AbstractPowerUpView(AbstractPowerUp powerUp) {
        this.powerUp = powerUp;

       
    }

    public void update(float dt) {
        if (powerUp != null && powerUp.b2body != null) {
            setPosition(powerUp.b2body.getPosition().x - getWidth() / 2, powerUp.b2body.getPosition().y - getHeight() / 2);
        } 

}
}
