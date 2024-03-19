package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.GameCreate;

public class PauseScreen implements Screen{


    // Reference to the main game object to switch screens.
    GameCreate game;

    // Stage for the pause screen
    Stage stage;

    // Skin for the buttons
    private Skin skin;

    OrthographicCamera camera;

    Viewport viewport;

    // Reference to the play screen so we can switch back to current instead of creating new upon "Resume"
    PlayScreen playScreen;

  

    public PauseScreen(GameCreate game, PlayScreen playScreen) {
        this.game = game;
        this.playScreen = playScreen;

        camera = new OrthographicCamera();
        camera.zoom = 2.5f;
        viewport = new FitViewport(GameCreate.V_Width, GameCreate.V_Height, camera);
        viewport.apply();
        
        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("glassy-ui.atlas"));
        skin = new Skin(Gdx.files.internal("glassy-ui.json"), atlas);
        
        createLayout();
    }
    
    private void createLayout() {
       Table table = new Table();
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table);

        // Adds buttons to the table
        TextButton resumeButton = new TextButton("Resume", skin);
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton mainMenuButton = new TextButton("Main Menu", skin);
        TextButton quitButton = new TextButton("Quit", skin);



        // Adds listeners to the buttons
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(playScreen);
            }
        });
        
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Handle option action
            }
        });

        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        // Add buttons to the table with padding
        table.add(resumeButton).pad(10);
        table.row(); // Move to the next row
        table.add(optionsButton).pad(10);
        table.row();
        table.add(mainMenuButton).pad(10);
        table.row();
        table.add(quitButton).pad(10);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }
        
    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        // To ensure that the PauseScreen stage does not handle input when it is not shown
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    
}
