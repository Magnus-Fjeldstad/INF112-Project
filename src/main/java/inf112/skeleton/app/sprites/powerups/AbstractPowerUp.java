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

public abstract class AbstractPowerUp extends Sprite  {
    
    protected PlayScreen screen;
    private World world;
    public Body b2body;
    private int startingX;
    private int startingY;
    private static final float powerUpDuration = 5;

    public AbstractPowerUp(PlayScreen screen, TextureAtlas.AtlasRegion region) {
        super(region);
        this.world = screen.getWorld(); 
        this.screen = screen;
        randomCoordinates();
        definePowerUp();
        setBounds(startingX / GameCreate.PPM, startingY/ GameCreate.PPM, 14 / GameCreate.PPM, 18 / GameCreate.PPM);

        setUserData();
    }


    //Generates random coordinates for the powerup
    protected void randomCoordinates() {
        Random rand = new Random();
        startingX = rand.nextInt(32, 450);
        startingY = rand.nextInt(32, 200);
    }

    protected abstract void removePowerUp();

    protected abstract void applyPowerUp();


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

        b2body.createFixture(fixtureDef);
        b2body.createFixture(fixtureDef).setUserData(GameCreate.CATEGORY_POWERUP);
    };


    public void update(float dt) {
        // this.powerUpDuration -= dt;
        // if (this.powerUpDuration <= 0) {
        //     removePowerUp();
        // }
    }

    private void setUserData() {
        b2body.setUserData(this);
    }
}   
