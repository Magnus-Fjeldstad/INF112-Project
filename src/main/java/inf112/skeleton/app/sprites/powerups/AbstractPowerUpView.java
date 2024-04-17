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

        initializeSprite();
    }

    protected void initializeSprite() {

        float width = texturePowerUp.getWidth() / GameCreate.PPM;
        float height = texturePowerUp.getHeight() / GameCreate.PPM;
        setSize(width, height);

        float x = (powerUp.getPosition().x - width / 2) /  GameCreate.PPM;
        float y = (powerUp.getPosition().y - height / 2) / GameCreate.PPM;
        setPosition(x, y);

        setRegion(textureRegionPowerUp);
        setScale(0.1f);
    }

    public AbstractPowerUp getPowerUp() {
        return powerUp;
    }
}