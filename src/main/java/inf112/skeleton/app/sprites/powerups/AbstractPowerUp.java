package inf112.skeleton.app.sprites.powerups;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import inf112.skeleton.app.GameCreate;


import inf112.skeleton.app.screens.PlayScreen;

public abstract class AbstractPowerUp extends Sprite  {
    
    protected PlayScreen screen;
    public Body b2body;
    private int startingX;
    private int startingY;

    public AbstractPowerUp(PlayScreen screen, TextureAtlas.AtlasRegion region) {
        super(region); 
        this.screen = screen;
        randomCoordinates();
        setPosition(startingX, startingY);
        definePowerUp();
    }


    //countdown for the powerup
    protected void startPowerUp() {
        applyPowerUp();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                removePowerUp();
            }
        }, 5000);

    }

    //Generates random coordinates for the powerup
    protected void randomCoordinates() {
        Random rand = new Random();
        startingX = rand.nextInt(1);
        startingY = rand.nextInt(1);
    }

    protected abstract void removePowerUp();

    protected abstract void applyPowerUp();


    private void definePowerUp(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / GameCreate.PPM, 32/ GameCreate.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = screen.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameCreate.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameCreate.CATEGORY_ENEMY; 
        fixtureDef.filter.maskBits = GameCreate.CATEGORY_WALLS | GameCreate.CATEGORY_FIREBALL | GameCreate.CATEGORY_PLAYER; 

        b2body.createFixture(fixtureDef);
        b2body.createFixture(fixtureDef).setUserData(GameCreate.CATEGORY_ENEMY);
    };

    
}   
