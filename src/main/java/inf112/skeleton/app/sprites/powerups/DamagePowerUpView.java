package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.GameCreate;

public class DamagePowerUpView extends AbstractPowerUpView {
    public DamagePowerUpView(AbstractPowerUp powerUp) {
        super(powerUp, "powerups/muscle.png");
        setScale(0.1f);
    }

    @Override
    protected void initializeSprite() {
        super.initializeSprite();
        setScale(0.05f);
        float x = (powerUp.getPosition().x - getWidth() * 4) / GameCreate.PPM;
        float y = (powerUp.getPosition().y - getHeight() * 4) / GameCreate.PPM;
        setPosition(x, y);
    }

}
