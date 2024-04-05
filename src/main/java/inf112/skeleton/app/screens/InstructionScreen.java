package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import inf112.skeleton.app.GameCreate;

public class InstructionScreen implements Screen{

    GameCreate game;
    Stage stage;
    private Skin skin;
    OrthographicCamera camera;
    Viewport viewport;

    public InstructionScreen(GameCreate game) {
        this.game = game;
        
        camera = new OrthographicCamera();
        camera.zoom = 2.5f;
        viewport = new FitViewport(GameCreate.V_Width, GameCreate.V_Height, camera);
        viewport.apply();
        
        stage = new Stage(viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("glassy-ui.atlas"));
        skin = new Skin(Gdx.files.internal("glassy-ui.json"), atlas);
        
        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.WHITE;

        // Create a Label with text for the screen
        Label creditsLabel = new Label("How to Play:\n", style);
        creditsLabel.setPosition(100, 200); // Position on the stage.
        creditsLabel.setSize(400, 300); // Assuming you want to give it some size.

        // Add Label to the Stage
        stage.addActor(creditsLabel);
        
        createLayout();
        
    }

    private void createLayout() {
        Table table = new Table();
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table);

        // Add button to the table
        TextButton backButton = new TextButton("Back", skin);

        // Back button to return to main menu
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(backButton).pad(10);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pause'");
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resume'");
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    
}
