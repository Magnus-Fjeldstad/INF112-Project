package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import inf112.skeleton.app.GameCreate;

public class PauseScreen implements Screen{

    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 100;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 100;

    // Reference to the main game object to switch screens.
    GameCreate game;

    // Textures for the pause screen buttons.
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonAcitive;
    Texture playButtonInactive;

    public PauseScreen(GameCreate game) {
        this.game = game;
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
        playButtonAcitive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
    }
    

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        
        // Calculate the center position for the buttons
        int x = (Gdx.graphics.getWidth() - EXIT_BUTTON_WIDTH) / 2;
        int exitY = 100; // Position for the exit button
        int playY = exitY + EXIT_BUTTON_HEIGHT + 20; // Position for the play button, with a margin of 20
        
        // Draw the exit button
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + EXIT_BUTTON_WIDTH &&
            Gdx.graphics.getHeight() - Gdx.input.getY() >= exitY && Gdx.graphics.getHeight() - Gdx.input.getY() <= exitY + EXIT_BUTTON_HEIGHT) {
            game.batch.draw(exitButtonActive, x, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.batch.draw(exitButtonInactive, x, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }
        
        // Draw the play button
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x + PLAY_BUTTON_WIDTH &&
            Gdx.graphics.getHeight() - Gdx.input.getY() >= playY && Gdx.graphics.getHeight() - Gdx.input.getY() <= playY + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(playButtonAcitive, x, playY, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        } else {
            game.batch.draw(playButtonInactive, x, playY, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }
        
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
    
}
