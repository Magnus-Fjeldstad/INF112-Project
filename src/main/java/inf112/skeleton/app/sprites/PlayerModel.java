package inf112.skeleton.app.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

/**
 * Class representing the player in the game.
 */

public class PlayerModel extends Sprite{
    public enum State {STANDING, LEFT, RIGHT, UP, DOWN};
    public State currentState;
    public State previousState;

    public PlayScreen screen;
    public Body b2body;
    public World world;
    private TextureRegion mainGuyStand;

    private Animation<TextureRegion> playerRun;
    private float stateTimer;



    public int health;
    public float movementSpeed;
    public int attackDamage;


    public PlayerModel(PlayScreen screen, int health, float movementSpeed, int attackDamage) {
        super(screen.getAtlas().findRegion("MainGuy"));
        this.screen = screen;
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;


        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i <= 3; i++) {
            frames.add(new TextureRegion(getTexture(), 0+(i*32) , 156, 32  , 32));
        }

        playerRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        defineEntity();

        mainGuyStand = new TextureRegion(getTexture(), 49, 120, 34, 34);
        setBounds(0,0, 16 / GameCreate.PPM, 16 / GameCreate.PPM);
        setRegion(mainGuyStand);
    }


    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case LEFT:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case RIGHT:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case UP:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case DOWN:
                region = playerRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = mainGuyStand;
                break;
        }
        return region;
    }

    public State getState() {
        if (b2body.getLinearVelocity().y > 0){
            return State.UP;
        }
        else if (b2body.getLinearVelocity().y < 0){
            return State.DOWN;
        }
        else if (b2body.getLinearVelocity().x > 0){
            return State.RIGHT;
        }
        else if (b2body.getLinearVelocity().x < 0){
            return State.LEFT;
        }
        return State.STANDING;

    }

    protected void defineEntity() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / GameCreate.PPM, 32 / GameCreate.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(9 / GameCreate.PPM);

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
