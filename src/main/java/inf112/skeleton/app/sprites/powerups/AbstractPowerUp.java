package inf112.skeleton.app.sprites.powerups;


import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.GameCreate;


import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.IEntity;
import inf112.skeleton.app.sprites.player.PlayerModel;

public abstract class AbstractPowerUp implements IEntity  {
    
    protected PlayScreen screen;
    protected PlayerModel playerModel;
    protected Vector2 position;
    private World world;
    public Body b2body;
    private int xPos;
    private int yPos;
    protected boolean isActive = false;
    protected boolean isRemovable = false;
    private float powerUpDuration = 10;
    private PowerUpEnum type;

    public AbstractPowerUp(PlayScreen screen, PlayerModel playerModel, PowerUpEnum type, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.position = new Vector2(xPos, yPos);
        this.type = type;
        this.world = screen.getWorld(); 
        this.screen = screen;
        this.playerModel = playerModel;
        definePowerUp();
    }

    protected abstract void removePowerUpEffect();

    protected abstract void applyPowerUpEffect();

    protected void removePowerUp(){
        removePowerUpEffect();
    }

    protected void applyPowerUp(){
        applyPowerUpEffect();
        isActive = true;
        dispose();
    }

    private void definePowerUp(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(xPos / GameCreate.PPM, yPos / GameCreate.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = screen.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameCreate.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameCreate.CATEGORY_POWERUP; 
        fixtureDef.filter.maskBits = GameCreate.CATEGORY_PLAYER; 
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef);
        b2body.createFixture(fixtureDef).setUserData(GameCreate.CATEGORY_POWERUP);
    }


    public void update(float dt) {
        if(isActive){
            powerUpDuration -= dt;
            if(powerUpDuration <= 0){
                removePowerUp();
                isRemovable = true;
            }
        }
    }

    public void dispose() {
        world.destroyBody(b2body);
    }
    
    public PowerUpEnum getType() {
        return type;
    }

    public Vector2 getPosition() {
        return position;
    }
}   
