package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;


import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class RedEnemy extends AbstractEnemy {

    private Body b2body;
    private TextureRegion enemyStand;

    public RedEnemy(PlayScreen screen, float x, float y, int health, float movementSpeed, int damage) {
        super(screen, x, y, health, movementSpeed, damage, screen.getAtlas().findRegion("SkeletonEnemy"));
        defineEnemy();
        enemyStand = new TextureRegion(getTexture(), 2, 2, 14, 18);
        setBounds(2, 2, 14 / GameCreate.PPM, 18 / GameCreate.PPM);
        setRegion(enemyStand);
    }
    
    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / GameCreate.PPM, 32/ GameCreate.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = screen.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameCreate.PPM);
        fixtureDef.shape = shape;

        fixtureDef.filter.categoryBits = GameCreate.CATEGORY_ENEMY; 
        fixtureDef.filter.maskBits = GameCreate.CATEGORY_WALLS | GameCreate.CATEGORY_FIRBALL | GameCreate.CATEGORY_PLAYER; 

        b2body.createFixture(fixtureDef);
        b2body.createFixture(fixtureDef).setUserData("RedEnemy");
    }

    @Override
    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }
    
}

