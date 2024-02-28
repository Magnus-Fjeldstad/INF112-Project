package inf112.skeleton.app.sprites;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.GameCreate;

/**
 * Class representing the player in the game.
 */
public class PlayerModel extends GameEntity {

    /**
     * Constructor for PlayerModel.
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
        bdef.position.set(32 / GameCreate.PPM, 32 / GameCreate.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameCreate.PPM);

        fdef.shape = shape;
        
        fdef.filter.categoryBits = GameCreate.CATEGORY_PLAYER; 
        fdef.filter.maskBits = GameCreate.CATEGORY_WALLS; 

        b2body.createFixture(fdef);
    }

    public float getSpeed() {
        return this.movementSpeed;
    }

    public void setSpeed(float addSpeed) {
        this.movementSpeed += addSpeed;
    }
}
