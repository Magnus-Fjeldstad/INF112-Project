package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.app.screens.GameOverScreen;
import inf112.skeleton.app.screens.MainMenuScreen;
import inf112.skeleton.app.screens.PlayScreen;


public class GameCreate extends Game {
	public SpriteBatch batch;

	//Bits for collison detection
	public static final short CATEGORY_DEFAULT = 1;
	public static final short CATEGORY_PLAYER = 2;
	public static final short CATEGORY_WALLS = 4;
	public static final short CATEGORY_FIREBALL = 8;
	public static final short CATEGORY_ENEMY = 16;
	public static final short CATEGORY_POWERUP = 32;

	//Gamescreen variables for the screen and PPM (Pixels pr. meter)
	public static final int V_Width = 400;
	public static final int V_Height = 208;
	public static final float PPM = 100;


	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
