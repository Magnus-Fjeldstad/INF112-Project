package inf112.skeleton.app.sprites.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GameCreate;

public abstract class AbstractPowerUpView extends Sprite {
    protected AbstractPowerUp powerUp;
    private final Texture texturePowerUp;
    private final TextureRegion textureRegionPowerUp;



    public AbstractPowerUpView(AbstractPowerUp powerUp, String texturePath) {
        this.powerUp = powerUp;
        this.texturePowerUp = new Texture(texturePath);
        this.textureRegionPowerUp = new TextureRegion(texturePowerUp);
        float width = 50 / GameCreate.PPM;
        float height = 50 / GameCreate.PPM;
        setBounds(powerUp.getPosition().x / GameCreate.PPM, powerUp.getPosition().y / GameCreate.PPM , width, height);
        System.out.println(texturePowerUp.getWidth() + " " + texturePowerUp.getHeight() + " " + GameCreate.PPM);
        setRegion(textureRegionPowerUp);
    }


    public AbstractPowerUp getPowerUp() {
        return powerUp;
    }
}