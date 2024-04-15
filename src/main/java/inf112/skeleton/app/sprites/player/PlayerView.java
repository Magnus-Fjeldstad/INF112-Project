package inf112.skeleton.app.sprites.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import inf112.skeleton.app.GameCreate;
import inf112.skeleton.app.screens.PlayScreen;

public class PlayerView extends Sprite {

    private final PlayerModel playerModel;

    public PlayerEnum currentState;
    public PlayerEnum previousState;

    private float stateTimer;

    //Texture regions for standing
    private TextureRegion mainGuyStand;
    private TextureRegion mainGuyStandLeft;
    private TextureRegion mainGuyStandRight;
    private TextureRegion mainGuyStandUp;

    //Animations
    private Animation<TextureRegion> playerRunUp;
    private Animation<TextureRegion> playerRunDown;
    private Animation<TextureRegion> playerRunLeft;
    private Animation<TextureRegion> playerRunRight;

    // Sets the size of the sprite
    private final int frameWidth = 32; // Width of each frame
    private final int frameHeight = 36; // Height of each frame
    private final int padding = 16; // Padding between characters



    public PlayerView(PlayScreen screen, PlayerModel playerModel) {
        super(screen.getAtlas().findRegion("MainGuy"));
        this.playerModel = playerModel;
        currentState = PlayerEnum.STANDING;
        previousState = PlayerEnum.STANDING;
        stateTimer = 0;
        initializeGraphics();
    }


    public void initializeGraphics() {
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

    /**
     * Returns the correct frame for the player based on the current state.
     * 
     * @param dt the time since the last frame
     * @return the correct frame for the player
     */
    public TextureRegion getFrame(float dt) {
        currentState = playerModel.getState();

        TextureRegion region = mainGuyStand; // Default to standing still frame if no other conditions met
        switch (currentState) {
            case UP:
                region = playerRunUp.getKeyFrame(stateTimer, true);
                previousState = PlayerEnum.UP;
                break;
            case DOWN:
                region = playerRunDown.getKeyFrame(stateTimer, true);
                previousState = PlayerEnum.DOWN;
                break;
            case LEFT:
                region = playerRunLeft.getKeyFrame(stateTimer, true);
                previousState = PlayerEnum.LEFT;
                break;
            case RIGHT:
                region = playerRunRight.getKeyFrame(stateTimer, true);
                previousState = PlayerEnum.RIGHT;
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

        stateTimer = currentState == previousState ? stateTimer + dt : 0;

        return region;
    }

     // Add this method to draw the health bar
    /**
     * Draws the player's health bar.
     * 
     * @param shapeRenderer The ShapeRenderer instance used for drawing shapes.
     */
    public void drawHealthBar(ShapeRenderer shapeRenderer) {
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

        float width = getWidth() * 0.8f; // Adjust the width to be ~20% narrower
        float x = playerModel.b2body.getPosition().x - width / 2; // Center the health bar over the player
        float y = playerModel.b2body.getPosition().y + getHeight() / 2 + 0.05f; // Position above the player
        float height = 0.02f; // Vertical thickness of the health bar

        float healthPercentage = (float) playerModel.getHealth() / playerModel.getMaxHealth();
        float greenWidth = width * healthPercentage; // Green portion based on current health

        shapeRenderer.setColor(Color.GREEN); // Draw green part
        shapeRenderer.rect(x, y, greenWidth, height);

        if (healthPercentage < 1) { // Draw red part only if health is not full
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(x + greenWidth, y, width - greenWidth, height);
        }
    }

    public void update(float dt) {
        setPosition(playerModel.b2body.getPosition().x - getWidth() / 2, playerModel.b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
        //playerModel.handleCollision();
    }
}
