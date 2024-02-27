package inf112.skeleton.app.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.GameTest;

/**
 * Class representing a fireball entity.
 */
public class Fireball extends GameEntity {

    private static final int DEFAULT_ATTACK_DAMAGE = 10;

    private Texture texture;
    private PlayerModel player;

    /**
     * Constructor for the Fireball.
     *
     * @param world  The game world this fireball belongs to.
     * @param startX The starting X position (center of the player).
     * @param startY The starting Y position (center of the player).
     */
    public Fireball(PlayerModel player, World world) {
        super(world, 0, 0, DEFAULT_ATTACK_DAMAGE);
        texture = new Texture("blackCircle.png");

        this.player = player;

        setPosition(player.b2body.getPosition().x, player.b2body.getPosition().y);

        defineEntity();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, player.b2body.getPosition().x, player.b2body.getPosition().y);
    }

    @Override
    protected void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(2 / GameTest.PPM);

        fdef.shape = shape;
        fdef.filter.categoryBits = GameTest.CATEGORY_FIREBALL; // Fireball category
        fdef.filter.maskBits = GameTest.CATEGORY_PLAYER; // Collide with walls only

        b2body.createFixture(fdef);
    }
}