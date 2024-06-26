package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.GameCreate;


public class GameOverScreen implements Screen{

    private final GameCreate game;
    private final Stage stage;
    private final Skin skin;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final Texture texture;
    private final Image image;
    
    public GameOverScreen(GameCreate game){
        this.game = game;
        
        camera = new OrthographicCamera();
        camera.zoom = 4.5f;
        viewport = new FitViewport(GameCreate.V_Width, GameCreate.V_Height, camera);
        viewport.apply();
        
        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("glassy-ui.atlas"));
        skin = new Skin(Gdx.files.internal("glassy-ui.json"), atlas);
        
        texture = new Texture(Gdx.files.internal("gameover.png"));
        image = new Image(texture);
        image.setPosition(-450, 0);
        

        // Add Label to the Stage
        stage.addActor(image);
        
        createLayout();
    }

    private void createLayout() {
        Table table = new Table();
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table);

        // Add button to the table
        TextButton mainMenuButton = new TextButton("Main Menu", skin);
        TextButton quitButton = new TextButton("Quit", skin);

        // Back button to return to main menu
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        // Quit button to close the game
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        table.add(mainMenuButton).padBottom(-800).expandX().fillX().spaceRight(250);
        table.add(quitButton).padBottom(-800).expandX().fillX().spaceLeft(250);
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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        texture.dispose();
    }
}
