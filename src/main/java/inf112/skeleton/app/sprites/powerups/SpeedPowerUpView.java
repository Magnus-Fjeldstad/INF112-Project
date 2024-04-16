package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.Texture;

public class SpeedPowerUpView extends AbstractPowerUpView{

    private Texture texture;
    
    public SpeedPowerUpView(AbstractPowerUp speedPowerUp) {
        super(speedPowerUp);

        texture = new Texture("powerups/muscle.png");
        setRegion(texture);
    }
}
