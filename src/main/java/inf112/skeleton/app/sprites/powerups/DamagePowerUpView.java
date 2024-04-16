package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.Texture;

public class DamagePowerUpView extends AbstractPowerUpView {

    private Texture texture;
    
    public DamagePowerUpView(AbstractPowerUp damagePowerUp) {
        super(damagePowerUp);

        texture = new Texture("powerups/muscle.png");
        setRegion(texture);
    }
}
