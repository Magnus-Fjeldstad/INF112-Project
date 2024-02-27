package inf112.skeleton.app.sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.GameTest;

/**
 * Class representing the player in the game.
 */
public class PlayerModel extends GameEntity {

    //Variables for collision between fireball, PlayerModel and B2World
    public static final short CATEGORY_PLAYER = 0x0001; // 0001 in binary
    public static final short CATEGORY_FIREBALL = 0x0002; // 0010 in binary
    public static final short CATEGORY_OTHER = 0x0004; // 0100 in binary

    /**
     * Constructor for PlayerModel.
     *
     * @param world         The game world this player belongs to.
     * @param health        Initial health of the player.
     * @param movementSpeed Movement speed of the player.
     * @param attackDamage  Attack damage of the player.
     */
    public PlayerModel(World world, int health, float movementSpeed, int attackDamage) {
        super(world, health, movementSpeed, attackDamage);
    }

    @Override
    protected void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameTest.PPM, 32 / GameTest.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameTest.PPM);

        fdef.shape = shape;
        
        fdef.filter.categoryBits = 1; // Player category
        fdef.filter.maskBits = 1; // Collide with walls only

        b2body.createFixture(fdef);
    }

    public float getSpeed() {
        return this.movementSpeed;
    }

    public void setSpeed(float addSpeed) {
        this.movementSpeed += addSpeed;
    }
}
