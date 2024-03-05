package inf112.skeleton.app.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

import com.badlogic.gdx.physics.box2d.Body;

public class Fireball {

    private Texture texture;
    private PlayerModel player;
    private PlayScreen screen;
    private World world;
    private Body b2body;
    private float x, y;

    public int damage;

    public Fireball(PlayerModel player, PlayScreen screen, int damage) {
        this.player = player;
        this.world = screen.getWorld();
        this.damage = player.getAttackDamage();

        this.texture = new Texture("blackCircle.png");

        // Assuming player.b2body.getPosition() returns a Vector2
        this.x = player.b2body.getPosition().x;
        this.y = player.b2body.getPosition().y;

        defineEntity();
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void setLinearVelocity(Vector2 velocity) {
        this.b2body.setLinearVelocity(velocity);
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
        fdef.filter.categoryBits = GameCreate.CATEGORY_FIRBALL;
        fdef.filter.maskBits = GameCreate.CATEGORY_WALLS | GameCreate.CATEGORY_ENEMY;

        b2body.createFixture(fdef);
    }
}
