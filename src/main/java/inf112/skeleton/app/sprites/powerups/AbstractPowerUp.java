package inf112.skeleton.app.sprites.powerups;


import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.GameCreate;


import inf112.skeleton.app.screens.PlayScreen;
import inf112.skeleton.app.sprites.IEntity;
import inf112.skeleton.app.sprites.player.PlayerModel;

public abstract class AbstractPowerUp extends Sprite implements IEntity  {
    
    protected PlayScreen screen;
    protected PlayerModel playerModel;
    private World world;
    public Body b2body;
    private int startingX;
    private int startingY;
    private boolean isActived = false;
    private static float powerUpDuration = 5;

    public AbstractPowerUp(PlayScreen screen, PlayerModel playerModel, TextureAtlas.AtlasRegion region) {
        super(region);
        this.world = screen.getWorld(); 
        this.screen = screen;
        this.playerModel = playerModel;
        randomCoordinates();
        definePowerUp();
        setBounds(startingX / GameCreate.PPM, startingY/ GameCreate.PPM, 14 / GameCreate.PPM, 18 / GameCreate.PPM);
        System.out.println("PowerUp created");
    }


    //Generates random coordinates for the powerup
    protected void randomCoordinates() {
        Random rand = new Random();
        startingX = rand.nextInt(32, 450);
        startingY = rand.nextInt(32, 200);
    }

    protected abstract void removePowerUpEffect();

    protected abstract void applyPowerUpEffect();

    protected void removePowerUp(){
        isActived = false;
        removePowerUpEffect();
    }

    protected void applyPowerUp(){
        System.out.println("PowerUp is applied");
        System.out.println(powerUpDuration);
        isActived = true;
        applyPowerUpEffect();
    }

    private void definePowerUp(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(startingX / GameCreate.PPM, startingY / GameCreate.PPM);
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
    };



    public void update(float dt) {
        System.out.println(isActived);
        if (isActived) {
            System.out.println("PowerUp is active");
            powerUpDuration -= dt;
            if (powerUpDuration <= 0) {
            }
            
        }
    }

    public void dispose() {
        world.destroyBody(b2body);
    }
    
}   
