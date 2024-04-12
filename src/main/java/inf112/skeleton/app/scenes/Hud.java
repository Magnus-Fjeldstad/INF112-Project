package inf112.skeleton.app.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GameCreate;

public class Hud implements Disposable{
    public Stage stage;
    private final Viewport viewport;
    
    private final Integer score;

    private final Label waveRound;
    private final Label waveLabel;
    private final Label gameNameLabel;

    public Hud(SpriteBatch sb) {
        score = 0;

        viewport = new FitViewport(GameCreate.V_Width, GameCreate.V_Height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        waveLabel = new Label("WAVE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        waveRound = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gameNameLabel = new Label("SURVIVE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(gameNameLabel).expandX().padTop(10);
        table.add(waveLabel).expandX().padTop(10);
        table.add(waveRound).expandX().padTop(10);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
