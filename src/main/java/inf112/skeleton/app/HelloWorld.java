package inf112.skeleton.app;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

public class HelloWorld implements ApplicationListener {
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture spriteImage;
	private Sound bellSound;
	private Rectangle spriteRect;
	private Rectangle screenRect = new Rectangle();

	// Added background and fireball texture
	private Texture backgroundImage;
	private Texture fireballImage;
	private float spriteRotation = 0; // Initialize with default rotation

	@Override
	public void create() {
		// Called at startup

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		spriteImage = new Texture(Gdx.files.internal("obligator.png"));
		// Background image
		backgroundImage = new Texture(Gdx.files.internal("prem.jpg"));
		fireballImage = new Texture(Gdx.files.internal("fireball.png"));

		spriteRect = new Rectangle(1, 1, spriteImage.getWidth() / 2, spriteImage.getHeight() / 2);
		bellSound = Gdx.audio.newSound(Gdx.files.internal("blipp.ogg"));
		Gdx.graphics.setForegroundFPS(60);
	}

	@Override
	public void dispose() {
		// Called at shutdown

		// Graphics and sound resources aren't managed by Java's garbage collector, so
		// they must generally be disposed of manually when no longer needed. But,
		// any remaining resources are typically cleaned up automatically when the
		// application exits, so these aren't strictly necessary here.
		// (We might need to do something like this when loading a new game level in
		// a large game, for instance, or if the user switches to another application
		// temporarily (e.g., incoming phone call on a phone, or something).
		batch.dispose();
		font.dispose();
		spriteImage.dispose();
		bellSound.dispose();
	}

	private ArrayList<Rectangle> fireballs = new ArrayList<>();

	@Override
	public void render() {
		// Called when the application should draw a new frame (many times per second).

		// This is a minimal example – don't write your application this way!

		// Draw the background image first
		batch.begin();
		batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();

		// Handle input events
		handleInput();

		// Draw calls should be wrapped in batch.begin() ... batch.end()
		batch.begin();
		font.draw(batch, "Hello, World!", 200, 200);
		batch.draw(spriteImage, spriteRect.x, spriteRect.y, spriteRect.width, spriteRect.height);
		batch.end();

		// Move the sprite
		moveSprite();
		spriteRotation = MathUtils.atan2(1, 1) * MathUtils.radiansToDegrees;

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			shootFireball();
		}

		// Render fireballs
		batch.begin();
		for (Rectangle fireball : fireballs) {
			batch.draw(fireballImage, fireball.x, fireball.y);
		}
		batch.end();

		// Don't handle input this way – use event handlers!
		if (Gdx.input.justTouched()) { // check for mouse click
			bellSound.play();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) { // check for key press
			Gdx.app.exit();
		}
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			spriteRect.x -= 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			spriteRect.x += 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			spriteRect.y += 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			spriteRect.y -= 1;
		}
	}

	private void shootFireball() {
		Rectangle fireball = new Rectangle();
		fireball.setSize(fireballImage.getWidth(), fireballImage.getHeight());
		fireball.setPosition(
				spriteRect.x + spriteRect.width / 2 - fireballImage.getWidth() / 2,
				spriteRect.y + spriteRect.height / 2 - fireballImage.getHeight() / 2);
		fireballs.add(fireball);

		// Determine fireball direction based on sprite direction (you may need to
		// adjust these values)
		float fireballSpeed = 5;
		float dx = MathUtils.cosDeg(spriteRotation) * fireballSpeed;
		float dy = MathUtils.sinDeg(spriteRotation) * fireballSpeed;

		// Move the fireball in the determined direction
		fireball.x += dx;
		fireball.y += dy;
	}

	private void moveSprite() {
		// Clamp sprite within screen bounds
		spriteRect.x = Math.max(0, Math.min(screenRect.width - spriteRect.width, spriteRect.x));
		spriteRect.y = Math.max(0, Math.min(screenRect.height - spriteRect.height, spriteRect.y));
	}

	@Override
	public void resize(int width, int height) {
		// Called whenever the window is resized (including with its original site at
		// startup)

		screenRect.width = width;
		screenRect.height = height;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
