package inf112.skeleton.app.sprites;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.GameCreate;

/**
 * Class representing the player in the game.
 */

public class PlayerModel{

    public World world;
    public Body b2body;

    public int health;
    public float movementSpeed;
    public int attackDamage;

    public PlayerModel(World world, int health, float movementSpeed, int attackDamage) {
        this.world = world;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        defineEntity();
    }


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

   
    public int getHealth() {
        return this.health;
    }

    public void setHealth(int deltaHealth){
        this.health += deltaHealth;
    }
    
    public float getSpeed() {
        return this.movementSpeed;
    }

    public void setSpeed(float deltaSpeed) {
        this.movementSpeed += deltaSpeed;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public void setAttack(int deltaAttackDamage) {
        this.attackDamage += deltaAttackDamage;
    }
}
