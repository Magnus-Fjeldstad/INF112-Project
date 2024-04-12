package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.scenes.scene2d.Actor;
import inf112.skeleton.app.sprites.player.PlayerModel;

import inf112.skeleton.app.GameCreate;

public class UpgradeMenuScreen implements Screen {

    private GameCreate game;
    private Stage stage;
    private Skin skin;

    // Reference to the player model
    private PlayerModel playerModel;

    public UpgradeMenuScreen(GameCreate game, PlayerModel playerModel) {
        this.game = game;
        this.playerModel = playerModel;

        stage = new Stage(new FitViewport(GameCreate.V_Width*1.5f, GameCreate.V_Height*4f));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("glassy-ui.json"));

        createLayout();
    }

    private void createLayout() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Add buttons to adjust upgrades
        TextButton healthUpgradeButton = new TextButton("Upgrade Health", skin);
        healthUpgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerModel.setMaxHealth( 10);
                System.out.println("Player's health upgraded to: " + playerModel.getMaxHealth());
            }
        });
        table.add(healthUpgradeButton).pad(10).row();

         // Add health regeneration upgrade button
         TextButton healthRegenUpgradeButton = new TextButton("Upgrade Health Regen", skin);
         healthRegenUpgradeButton.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 // Increment health regeneration by 1
                 playerModel.setHealthRegen(playerModel.getHealthRegen()+1);
                 System.out.println("Player's Health Regeneration upgraded to: " + playerModel.getHealthRegen());
             }
         });
         table.add(healthRegenUpgradeButton).pad(10).row();

          // Add movement speed upgrade button
         TextButton speedUpgradeButton = new TextButton("Upgrade Speed", skin);
         speedUpgradeButton.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
             playerModel.setSpeed(1);
             System.out.println("Player's speed upgraded to: " + playerModel.getSpeed());
        }
    });
    table.add(speedUpgradeButton).pad(10).row();

        // Add more buttons for other upgrades...

        // Add a back button
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(backButton).pad(10);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
