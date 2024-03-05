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

public class PlayerModel extends Sprite {
    public enum State {
        STANDING, LEFT, RIGHT, UP, DOWN
    };

    public State currentState;
    public State previousState;

    public PlayScreen screen;
    public Body b2body;
    public World world;

    private TextureRegion mainGuyStand;
    private TextureRegion mainGuyStandLeft;
    private TextureRegion mainGuyStandRight;
    private TextureRegion mainGuyStandUp;

    private Animation<TextureRegion> playerRunUp;
    private Animation<TextureRegion> playerRunDown;
    private Animation<TextureRegion> playerRunLeft;
    private Animation<TextureRegion> playerRunRight;

    // Sets the size of the sprite
    private int frameWidth = 32; // Width of each frame
    private int frameHeight = 35; // Height of each frame
    private int padding = 16; // Padding between characters

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

        // Down animation
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * (frameWidth + padding), 120, frameWidth, frameHeight));
        }
        playerRunDown = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // Right animation
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * (frameWidth + padding), 155, frameWidth, frameHeight));
        }
        playerRunRight = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // Up animation
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * (frameWidth + padding), 191, frameWidth, frameHeight));
        }
        playerRunUp = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // Left animation
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), i * (frameWidth + padding), 226, frameWidth, frameHeight));
        }
        playerRunLeft = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        // Set the player's health, speed and attack damage
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.attackDamage = attackDamage;
        defineEntity();

        // Texture regions for when the player is standing still
        mainGuyStand = new TextureRegion(getTexture(), 0, 120, frameWidth, frameHeight);
        mainGuyStandLeft = new TextureRegion(getTexture(), 0, 226, frameWidth, frameHeight);
        mainGuyStandRight = new TextureRegion(getTexture(), 0, 155, frameWidth, frameHeight);
        mainGuyStandUp = new TextureRegion(getTexture(), 0, 191, frameWidth, frameHeight);

        // Set the size of the sprite and the default frame
        float scale = 0.75f;
        setBounds(0, 0, (frameWidth * scale) / GameCreate.PPM, (frameHeight * scale) / GameCreate.PPM);
        setRegion(mainGuyStand);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region = mainGuyStand; // Default to standing still frame if no other conditions met
        switch (currentState) {
            case UP:
                region = playerRunUp.getKeyFrame(stateTimer, true);
                previousState = State.UP; 
                break;
            case DOWN:
                region = playerRunDown.getKeyFrame(stateTimer, true);
                previousState = State.DOWN; 
                break;
            case LEFT:
                region = playerRunLeft.getKeyFrame(stateTimer, true);
                previousState = State.LEFT; 
                break;
            case RIGHT:
                region = playerRunRight.getKeyFrame(stateTimer, true);
                previousState = State.RIGHT; 
                break;
            case STANDING:
                // Choose the standing still frame based on the previousState
                if (previousState != null) {
                    switch (previousState) {
                        case UP:
                            region = mainGuyStandUp;
                            break;
                        case LEFT:
                            region = mainGuyStandLeft;
                            break;
                        case RIGHT:
                            region = mainGuyStandRight;
                            break;

                        default:
                            break;
                    }
                }
                break;
        }

        if (currentState != State.STANDING) {
            stateTimer += dt;
        } else {
            stateTimer = 0;
        }

        return region;
    }

    public State getState() {
        float xVelocity = b2body.getLinearVelocity().x;
        float yVelocity = b2body.getLinearVelocity().y;
        boolean movingHorizontally = Math.abs(xVelocity) > Math.abs(yVelocity);

        if (movingHorizontally) {
            if (xVelocity > 0) {
                return State.RIGHT;
            } else if (xVelocity < 0) {
                return State.LEFT;
            }
        } else {
            if (yVelocity > 0) {
                return State.UP;
            } else if (yVelocity < 0) {
                return State.DOWN;
            }
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
        shape.setRadius(11 / GameCreate.PPM);

        fdef.shape = shape;

        fdef.filter.categoryBits = GameCreate.CATEGORY_PLAYER;
        fdef.filter.maskBits = GameCreate.CATEGORY_WALLS;

        b2body.createFixture(fdef);
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int deltaHealth) {
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
