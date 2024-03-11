package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class RedEnemy extends AbstractEnemy {

    private World world;
    private Body b2body;
    private TextureRegion enemyStand;

    public RedEnemy(World world, float x, float y, int health, float movementSpeed, int damage, PlayScreen screen) {
        super(screen, x, y, health, movementSpeed, damage, screen.getAtlas().findRegion("SkeletonEnemy"));
        this.world = world;
        defineBody();
        enemyStand = new TextureRegion(getTexture(), 2, 2, 14, 18);
        setBounds(2, 2, 14 / GameCreate.PPM, 18 / GameCreate.PPM);
        setRegion(enemyStand);
    }
    

    protected void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / GameCreate.PPM, 32/ GameCreate.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

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

    @Override
    protected void defineEnemy() {}
    
}

