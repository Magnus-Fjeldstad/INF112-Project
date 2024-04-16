package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.sprites.player.PlayerModel;

import inf112.skeleton.app.GameCreate;

public class MainMenuScreen implements Screen {

    private final GameCreate game;
    private final Stage stage;
    private final Skin skin;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Texture backgroundTexture;
    private Image backgroundImage;

    public MainMenuScreen(GameCreate game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.zoom = 1.0f;
        viewport = new ScreenViewport(camera);
        viewport.apply();

        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("glassy-ui.atlas"));
        skin = new Skin(Gdx.files.internal("glassy-ui.json"), atlas);

        backgroundTexture = new Texture(Gdx.files.internal("gamebackground.jpg"));

        createLayout();
    }

    private void createLayout() {
        Table table = new Table();
        table.setFillParent(true); // Make the table fill the stage
    
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        stage.addActor(backgroundImage);
        stage.addActor(table);

        
        // Adds buttons to the table
        TextButton startButton = new TextButton("Start", skin);
        TextButton upgradeButton = new TextButton("Upgrades", skin);
        TextButton instructionsButton = new TextButton("How To Play", skin);
        TextButton creditsButton = new TextButton("Credits", skin);
        TextButton quitButton = new TextButton("Quit", skin);
        
        
        // Button to start a new game
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game));
            }
        });
        
        // Button to open the upgrade menu
        upgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Pass the required arguments to create PlayerModel
                PlayerModel playerModel = new PlayerModel(new PlayScreen(game));
                game.setScreen(new UpgradeMenuScreen(game, playerModel));
            }
        });
        
        instructionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new InstructionScreen(game));
            }
        });
        
        creditsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new CreditsScreen(game));
            }
        });
        
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        
        // Add buttons to the table with padding, then move to the next row
        table.add(startButton).pad(10).row();
        table.add(upgradeButton).pad(10).row();
        table.add(instructionsButton).pad(10).row();
        table.add(creditsButton).pad(10).row();
        table.add(quitButton).pad(10).row();
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
        stage.getViewport().apply();

        backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
}


    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        // To ensure that the MainMenuScreen stage does not handle input when it is not shown
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        backgroundTexture.dispose();
    }
}
