package inf112.skeleton.app.sprites.enemies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

public class Enemy1 extends AbstractEnemy {

    private Boolean isAlive;


    public Enemy1(PlayScreen screen, float x, float y, int health, float movementSpeed, int attackDamage) {
        super(screen, x, y, health, movementSpeed, attackDamage);
        this.isAlive = true;
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameCreate.PPM, 32 / GameCreate.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / GameCreate.PPM);

        fdef.shape = shape;

        fdef.filter.categoryBits = GameCreate.CATEGORY_ENEMY;
        fdef.filter.maskBits = GameCreate.CATEGORY_WALLS;

        b2body.createFixture(fdef);
    }

    @Override
    public void update(float dt) {
        float newX = b2body.getPosition().x + movementSpeed * dt;
        float newY = b2body.getPosition().y;
        b2body.setTransform(newX, newY, b2body.getAngle());
    }

    public void draw(Batch batch) {
        if (isAlive) {
            super.draw(batch);
        }
    }
}
