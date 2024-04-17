package inf112.skeleton.app.sprites.powerups;

import inf112.skeleton.app.GameCreate;

public class SpeedPowerUpView extends AbstractPowerUpView {
    public SpeedPowerUpView(AbstractPowerUp powerUp) {
        super(powerUp, "powerups/boots.png");
    }

    @Override
    protected void initializeSprite() {
        super.initializeSprite();
        setScale(0.05f);
        float x = (powerUp.getPosition().x - getWidth()) / GameCreate.PPM;
        float y = (powerUp.getPosition().y - getHeight()) / GameCreate.PPM;
        setPosition(x, y);
    }
}