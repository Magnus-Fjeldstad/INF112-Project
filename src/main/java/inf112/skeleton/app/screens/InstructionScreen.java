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

public class InstructionScreen implements Screen {

    private GameCreate game;
    private Stage stage;
    private Skin skin;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture texture;
    private Image image;


    public InstructionScreen(GameCreate game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.zoom = 4.5f;
        viewport = new FitViewport(GameCreate.V_Width, GameCreate.V_Height, camera);
        viewport.apply();

        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("glassy-ui.atlas"));
        skin = new Skin(Gdx.files.internal("glassy-ui.json"), atlas);

        // Load the texture
        texture = new Texture(Gdx.files.internal("instruksjon.png"));
        // Create an Image actor with the texture
        image = new Image(texture);
        // Set the position of the image (adjust as needed)
        image.setPosition(-500, -500);
        // Add the image to the stage
        stage.addActor(image);

        createLayout();

    }

    private void createLayout() {
        Table table = new Table();
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table);
    
        // Add button to the table
        TextButton backButton = new TextButton("Back", skin);
    
        // Set the size of the button (adjust as needed)
        backButton.setSize(100, 50); // Width, Height
    
        // Back button to return to main menu
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    
        // Add the button to the table with padding
        table.add(backButton).padBottom(-800); // Move the button further down
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
