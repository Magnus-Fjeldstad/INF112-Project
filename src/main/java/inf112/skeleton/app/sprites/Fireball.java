package inf112.skeleton.app.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Fireball extends Sprite {

    private World world;
    public Body b2body;
    private float x, y;
    private TextureRegion fireballTexture;

    public int damage;

    /**
     * Constructor for Fireball class.
     *
     * @param screen The play screen.
     * @param damage The damage of the fireball.
     * @param atlas The texture atlas containing the fireball texture.
     */
    public Fireball(PlayScreen screen, int damage, TextureAtlas atlas) {
        this.world = screen.getWorld();
        this.damage = damage;
        this.x = screen.getPlayerModel().b2body.getPosition().x;
        this.y = screen.getPlayerModel().b2body.getPosition().y;
        fireballTexture = new TextureRegion(atlas.findRegion("SkeletonEnemy"), 2, 2, 14, 18); // Assuming SkeletonEnemy is the region name
        setBounds(x, y, 14 / GameCreate.PPM, 18 / GameCreate.PPM);
        setRegion(fireballTexture);
        defineEntity();
    }

    protected void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2 / GameCreate.PPM);

        fdef.shape = shape;
        fdef.filter.categoryBits = GameCreate.CATEGORY_FIREBALL;
        fdef.filter.maskBits = GameCreate.CATEGORY_WALLS | GameCreate.CATEGORY_ENEMY ;

        b2body.createFixture(fdef).setUserData(GameCreate.CATEGORY_FIREBALL);
    }

     public void setLinearVelocity(Vector2 velocity) {
        this.b2body.setLinearVelocity(velocity);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }
}
